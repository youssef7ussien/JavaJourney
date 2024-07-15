-- Insert some test data for the development stage.

-- Insert sample users
INSERT INTO users (username, balance) VALUES 
('h2_user_01', 1000.00),
('h2_user_02', 500.00),
('h2_user_03', 2000.00),
('h2_user_04', 32340.00),
('h2_user_05', 45.5);
('h2_user_06', 500.00),
('h2_user_07', 2800.00),
('h2_user_08', 32340.00),
('h2_user_09', 4745.53),
('h2_user_10', 3500.00);

-- Insert sample transactions
INSERT INTO transactions (sender_id, receiver_id, amount) VALUES
(1, 2, 4434.00),
(4, 5, 1300.00),
(7, 8, 2900.00),
(10, 9, 10090.00),
(4, 3, 8090.00),
(3, 1, 500.00);
