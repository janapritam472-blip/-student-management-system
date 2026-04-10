-- Student Management System - Database Schema
-- Compatible with MySQL / PostgreSQL / H2

CREATE TABLE IF NOT EXISTS students (
    id          VARCHAR(10)  PRIMARY KEY,
    first_name  VARCHAR(100) NOT NULL,
    last_name   VARCHAR(100) NOT NULL,
    email       VARCHAR(255) NOT NULL UNIQUE,
    phone       VARCHAR(20),
    course      VARCHAR(100) NOT NULL,
    grade       VARCHAR(5),
    status      VARCHAR(20)  NOT NULL DEFAULT 'Active',
    enrolled_at DATE         NOT NULL DEFAULT CURRENT_DATE,
    created_at  TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at  TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP
);

-- Sample seed data
INSERT INTO students (id, first_name, last_name, email, phone, course, grade, status, enrolled_at) VALUES
('STU-0001', 'Aanya',   'Sharma',    'aanya.sharma@uni.edu',    '+91-9876543210', 'Computer Science',    'A',  'Active',   '2023-07-15'),
('STU-0002', 'Rohan',   'Verma',     'rohan.verma@uni.edu',     '+91-9823456781', 'Data Science',        'B+', 'Active',   '2023-07-15'),
('STU-0003', 'Priya',   'Patel',     'priya.patel@uni.edu',     '+91-9712345678', 'Electrical Eng.',     'A+', 'Active',   '2022-07-20'),
('STU-0004', 'Kiran',   'Nair',      'kiran.nair@uni.edu',      '+91-9634567891', 'Mechanical Eng.',     'B',  'Inactive', '2022-07-20'),
('STU-0005', 'Devansh', 'Mishra',    'devansh.mishra@uni.edu',  '+91-9512345678', 'Business Admin.',     'A',  'Active',   '2024-01-10');
