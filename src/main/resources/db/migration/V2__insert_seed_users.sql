-- Senhas serão bcrypt; para facilitar aqui insira senhas já encoded (veja DataInitializer se preferir)
INSERT INTO usuario (username, password, nome, role, enabled) VALUES
('admin', '$2a$10$7sYfF0zv1O7sK/7q2P1q7O2VSe9g8i8YH8f8z3y5QpK8a1u1eH6qW', 'Administrador', 'ROLE_ADMIN', TRUE),
('user', '$2a$10$hQe6k1f8xJ9pZ/1c2l5t9uKJf4c9g3k7m2t2z5y8r1s9u3v4wXyZa', 'Usuário Comum', 'ROLE_USER', TRUE);
-- (essas strings são placeholders — idealmente gere via BCrypt. Alternativamente utilize DataInitializer para criar.)
