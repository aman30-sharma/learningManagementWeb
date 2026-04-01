-- EduMatrix Database Schema
-- MySQL 8.0+

CREATE DATABASE IF NOT EXISTS edumatrix;
USE edumatrix;

-- Users table
CREATE TABLE IF NOT EXISTS users (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    full_name VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    role VARCHAR(50) NOT NULL,
    phone VARCHAR(20),
    avatar VARCHAR(255),
    created_at DATETIME NOT NULL,
    last_login DATETIME,
    active BOOLEAN NOT NULL DEFAULT TRUE,
    INDEX idx_username (username),
    INDEX idx_email (email),
    INDEX idx_role (role)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Courses table
CREATE TABLE IF NOT EXISTS courses (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    description TEXT,
    instructor_id BIGINT,
    category VARCHAR(100),
    thumbnail VARCHAR(255),
    duration INT,
    created_at DATETIME NOT NULL,
    published BOOLEAN NOT NULL DEFAULT FALSE,
    FOREIGN KEY (instructor_id) REFERENCES users(id) ON DELETE SET NULL,
    INDEX idx_instructor (instructor_id),
    INDEX idx_category (category),
    INDEX idx_published (published)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Modules table
CREATE TABLE IF NOT EXISTS modules (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    content TEXT,
    course_id BIGINT,
    order_index INT,
    video_url VARCHAR(500),
    document_url VARCHAR(500),
    FOREIGN KEY (course_id) REFERENCES courses(id) ON DELETE CASCADE,
    INDEX idx_course (course_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Enrollments table
CREATE TABLE IF NOT EXISTS enrollments (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    student_id BIGINT,
    course_id BIGINT,
    enrolled_at DATETIME NOT NULL,
    progress INT DEFAULT 0,
    completed_at DATETIME,
    grade DOUBLE,
    FOREIGN KEY (student_id) REFERENCES users(id) ON DELETE CASCADE,
    FOREIGN KEY (course_id) REFERENCES courses(id) ON DELETE CASCADE,
    UNIQUE KEY unique_enrollment (student_id, course_id),
    INDEX idx_student (student_id),
    INDEX idx_course (course_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Activities table
CREATE TABLE IF NOT EXISTS activities (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id BIGINT,
    course_id BIGINT,
    activity_type VARCHAR(50) NOT NULL,
    timestamp DATETIME NOT NULL,
    duration INT,
    details TEXT,
    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE,
    FOREIGN KEY (course_id) REFERENCES courses(id) ON DELETE CASCADE,
    INDEX idx_user (user_id),
    INDEX idx_course (course_id),
    INDEX idx_type (activity_type),
    INDEX idx_timestamp (timestamp)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Performance table
CREATE TABLE IF NOT EXISTS performance (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    student_id BIGINT,
    course_id BIGINT,
    quiz_score INT,
    assignment_score INT,
    participation_score INT,
    overall_grade DOUBLE,
    recorded_at DATETIME NOT NULL,
    time_spent INT,
    modules_completed INT,
    FOREIGN KEY (student_id) REFERENCES users(id) ON DELETE CASCADE,
    FOREIGN KEY (course_id) REFERENCES courses(id) ON DELETE CASCADE,
    INDEX idx_student (student_id),
    INDEX idx_course (course_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
