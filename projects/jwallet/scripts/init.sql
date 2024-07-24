-- +--------+
-- | Tables |
-- +--------+

CREATE TABLE IF NOT EXISTS users (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(255) NOT NULL,
    balance DECIMAL(19, 2) NOT NULL
);

CREATE TABLE IF NOT EXISTS transactions (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    sender_id BIGINT NOT NULL,
    receiver_id BIGINT NOT NULL,
    amount DECIMAL(19, 2) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP(),
    FOREIGN KEY (sender_id) REFERENCES users(id),
    FOREIGN KEY (receiver_id) REFERENCES users(id)
);

-- +------+
-- | Data |
-- +------+

-- Insert sample users
INSERT INTO users (username, balance) VALUES 
('mysql_user_01', 1000.00),
('mysql_user_02', 500.00),
('mysql_user_03', 2000.00),
('mysql_user_04', 32340.00),
('mysql_user_05', 45.5),
('mysql_user_06', 500.00),
('mysql_user_07', 2800.00),
('mysql_user_08', 32340.00),
('mysql_user_09', 4745.53),
('mysql_user_10', 3500.00);

-- Insert sample transactions
INSERT INTO transactions (sender_id, receiver_id, amount) VALUES
(1, 2, 4434.00),
(4, 5, 1300.00),
(7, 8, 2900.00),
(10, 9, 10090.00),
(4, 3, 8090.00),
(3, 1, 500.00);
