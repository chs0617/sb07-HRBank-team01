-- UUID 자동 생성을 쓰고 싶으면 한 번만 실행
-- CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

-- 직원
CREATE TABLE employees (
                           id              uuid            PRIMARY KEY,
                           job_position    varchar(50)     NOT NULL,
                           created_at      timestamp       NOT NULL,
                           updated_at      timestamp,
                           name            varchar(50)     NOT NULL,
                           email           varchar(100)    NOT NULL UNIQUE,
                           department_id   uuid            NOT NULL,
                           hire_date       timestamp       NOT NULL,
                           status          varchar(30)     NOT NULL,
                           profile_id      uuid,
                           employee_no     varchar(50)     NOT NULL UNIQUE
);

-- 부서
CREATE TABLE departments (
                             id              uuid            PRIMARY KEY,
                             created_at      timestamp       NOT NULL,
                             updated_at      timestamp,
                             name            varchar(50)     NOT NULL UNIQUE,
                             description     varchar(200)    NOT NULL,
                             establish_date  date            NOT NULL
);

-- 파일
CREATE TABLE files (
                       id          uuid            PRIMARY KEY,
                       created_at  timestamp       NOT NULL,
                       name        varchar(100)    NOT NULL,
                       type        varchar(100)    NOT NULL,
                       size        bigint          NOT NULL
);

-- 직원 수정 이력 헤더
CREATE TABLE employee_histories (
                                    id              uuid            PRIMARY KEY,
                                    type            varchar(30)     NOT NULL,   -- enum은 애플리케이션에서 관리
                                    memo            varchar(255),
                                    ip_address      varchar(255),
                                    created_at      timestamp       NOT NULL,
                                    employee_id     uuid            NOT NULL
);

-- 백업 이력
CREATE TABLE backups (
                         id              uuid            PRIMARY KEY,
                         created_at      timestamp       NOT NULL,
                         worker          varchar(50)     NOT NULL,   -- 작업자 IP or 'system'
                         start_time      timestamp       NOT NULL,
                         end_time        timestamp       NOT NULL,
                         status          varchar(30)     NOT NULL,   -- '진행중','완료','실패','건너뜀'
                         file_id         uuid            NOT NULL
);

-- 이력 상세
CREATE TABLE history_details (
                                 id              uuid            PRIMARY KEY,
                                 property_name   varchar(100)    NOT NULL,
                                 before_value    text,
                                 after_value     text,
                                 history_id      uuid            NOT NULL,
                                 created_at      timestamp       NOT NULL
);

-- ===== FK (필요 없으면 이 블록은 빼도 됨) =====

ALTER TABLE employees
    ADD CONSTRAINT fk_employees_department
        FOREIGN KEY (department_id) REFERENCES departments(id);

ALTER TABLE employees
    ADD CONSTRAINT fk_employees_profile
        FOREIGN KEY (profile_id) REFERENCES files(id);

ALTER TABLE employee_histories
    ADD CONSTRAINT fk_histories_employee
        FOREIGN KEY (employee_id) REFERENCES employees(id);

ALTER TABLE backups
    ADD CONSTRAINT fk_backups_file
        FOREIGN KEY (backup_file_id) REFERENCES files(id);

ALTER TABLE history_details
    ADD CONSTRAINT fk_history_details_history
        FOREIGN KEY (history_id) REFERENCES employee_histories(id);

