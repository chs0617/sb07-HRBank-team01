package com.codeit_team01.sb07_hrbank_team01.history.repository;

import com.codeit_team01.sb07_hrbank_team01.common.dto.response.PageResponseDto;
import com.codeit_team01.sb07_hrbank_team01.history.dto.requestDto.HistorySearchCondition;
import com.codeit_team01.sb07_hrbank_team01.history.dto.requestDto.HistorySearchCondition.HistorySortType;
import com.codeit_team01.sb07_hrbank_team01.history.dto.responseDto.HistoryChangeLogDto;
import com.codeit_team01.sb07_hrbank_team01.history.entity.History;
import com.codeit_team01.sb07_hrbank_team01.history.entity.HistoryType;
import com.codeit_team01.sb07_hrbank_team01.history.uils.CursorUtils;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.time.Instant;
import java.util.List;

import static com.codeit_team01.sb07_hrbank_team01.employee.entity.QEmployee.employee;
import static com.codeit_team01.sb07_hrbank_team01.history.entity.QHistory.history;

@RequiredArgsConstructor
public class HistoryRepositoryImpl implements HistoryRepositoryCustom {
    private final JPAQueryFactory queryFactory;

    @Override
    public PageResponseDto<HistoryChangeLogDto> searchHistoriesWithCursor(HistorySearchCondition condition) {
        int pageSize = condition.getPageSize();
        HistorySortType sortType = condition.getSortTypeOrDefault();

        //데이터 조회
        List<History> histories = queryFactory
                .selectFrom(history)
                .leftJoin(history.employee, employee).fetchJoin()
                .where(
                        //검색 조건(AND)
                        employeeNumberContains(condition.getEmployeeNo()),
                        memoContains(condition.getMemo()),
                        ipAddressContains(condition.getIpAddress()),
                        createAtBetween(condition.getStartDate(), condition.getEndDate()),
                        typeEquals(condition.getType()),
                        //커서조건
                        cursorCondition(condition.getCursorId(), sortType)
                )
                .orderBy(getOrderSpecifiers(sortType))
                .limit(pageSize + 1)
                .fetch();
        boolean hasNext = histories.size() > pageSize;

        //실제 반환 데이터
        List<History> content = hasNext ? histories.subList(0, pageSize) : histories;

        // DTO변환
        List<HistoryChangeLogDto> dtoList = content.stream()
                .map(HistoryChangeLogDto::from)
                .toList();

        //전체 개수 조회
        Long totalElements = queryFactory
                .select(history.count())
                .from(history)
                .leftJoin(history.employee, employee)
                .where(
                        //커서조건 제외, 검색 조건만 허용
                        employeeNumberContains(condition.getEmployeeNo()),
                        memoContains(condition.getMemo()),
                        ipAddressContains(condition.getIpAddress()),
                        createAtBetween(condition.getStartDate(), condition.getEndDate()),
                        typeEquals(condition.getType())
                )
        .fetchOne();

        // nextCursor계산
        Object nextCursor = null;
        Long nextIdAfter = null;


        if(!dtoList.isEmpty() && hasNext){
            nextIdAfter = dtoList.get(dtoList.size() - 1).id();
            nextCursor = CursorUtils.encodeCursor(nextIdAfter);
        }

        //PageResponseDto
        return new PageResponseDto<>(
                dtoList,
                nextCursor,
                nextIdAfter,
                pageSize,
                totalElements != null ? totalElements : 0L,
                hasNext
        );
    }

    //사번 부분 일치
    private BooleanExpression employeeNumberContains(String employeeNumber) {
        return (employeeNumber == null || employeeNumber.isBlank())
                ? null : employee.employeeNo.contains(employeeNumber);
    }
    //메모 부분 일치
    private BooleanExpression memoContains(String memo) {
        return (memo == null || memo.isBlank())
                ? null : history.memo.contains(memo);
    }
    //ip주소 부분 일치
    private BooleanExpression ipAddressContains(String ipAddress) {
        return (ipAddress == null || ipAddress.isBlank())
                ? null : history.ipAddress.contains(ipAddress);
    }
    //날짜 범위
    private BooleanExpression createAtBetween(Instant startDate, Instant endDate) {
        if(startDate != null && endDate != null){
            return history.createdAt.between(startDate, endDate);
        }else if(startDate != null){
            return history.createdAt.goe(startDate);
        }else if(endDate != null){
            return history.createdAt.loe(endDate);
        }
        return null;
    }
    private BooleanExpression typeEquals(HistoryType type) {
        return type == null ? null : history.type.eq(type);
    }

    //커서 조건
    private BooleanExpression cursorCondition(Long cursorId, HistorySortType sortType) {
        if(cursorId == null){
            return null;
        }
        return switch(sortType){
            case TIME_DESC, IP_DESC -> history.id.lt(cursorId);
            case TIME_ASC, IP_ASC -> history.id.eq(cursorId);
        };
    }

    //정렬 조건 생성
    private OrderSpecifier<?>[] getOrderSpecifiers(HistorySortType sortType) {
        return switch(sortType){
            case TIME_DESC -> new OrderSpecifier<?>[]{
                    history.createdAt.desc(),
                    history.id.desc()
            };
            case TIME_ASC -> new OrderSpecifier<?>[]{
                    history.createdAt.asc(),
                    history.id.asc()
            };
            case IP_DESC -> new OrderSpecifier<?>[]{
                    history.ipAddress.desc(),
                    history.id.desc()
            };
            case IP_ASC -> new OrderSpecifier<?>[]{
                    history.ipAddress.asc(),
                    history.id.asc()
            };
        };
    }
}
