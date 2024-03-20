-- Insert sample users
INSERT INTO users (username, balance) VALUES 
('user1', 1000.00),
('user2', 500.00),
('user3', 2000.00),
('user4', 32340.00),
('user5', 45.5);

-- Insert sample transactions
INSERT INTO transactions (sender_id, receiver_id, amount) VALUES
(1, 2, 100.00),
(4, 5, 100.00),
(3, 1, 500.00);