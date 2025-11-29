-- Jeux
INSERT INTO games (id, title, platform, genre, price, rating, release_date, stock, description, cover_image, url_trailer, promo, popular) VALUES 
('1', 'Cyberpunk 2077', 'PC', 'RPG', 59.99, 4.5, '2020-12-10', 12, 'Incarnez V, un mercenaire dans la mégalopole futuriste de Night City...', '/assets/games/cover/cyberpunk.jpg', 'https://www.youtube.com/watch?v=8X2kIfS6fb8', true, false),
('2', 'Red Dead Redemption 2', 'PC', 'Aventure', 49.99, 4.7, '2018-10-26', 18, 'Vivez l''Amérique à la fin de l''ère du Far West...', '/assets/games/cover/rdr2.jpg', 'https://www.youtube.com/watch?v=gmA6MrX81z4', false, false),
('3', 'The Witcher 3: Wild Hunt', 'PC', 'RPG', 39.99, 4.9, '2015-05-19', 20, 'Geralt de Rivia, chasseur de monstres légendaire...', '/assets/games/cover/witcher3.jpg', 'https://www.youtube.com/watch?v=c0i88t0Kacs', false, true);

-- Tags des jeux
INSERT INTO game_tags (game_id, tags) VALUES
('1', 'RPG'), ('1', 'Cyberpunk'), ('1', 'Open World'),
('2', 'Aventure'), ('2', 'Western'), ('2', 'Open World'),
('3', 'RPG'), ('3', 'Fantasy'), ('3', 'Open World');

-- Images des jeux
INSERT INTO game_images (game_id, images) VALUES
('1', '/assets/games/screenshots/cyberpunk1.jpg'),
('1', '/assets/games/screenshots/cyberpunk2.jpg'),
('2', '/assets/games/screenshots/rdr2_1.jpg'),
('2', '/assets/games/screenshots/rdr2_2.jpg'),
('3', '/assets/games/screenshots/witcher3_1.jpg'),
('3', '/assets/games/screenshots/witcher3_2.jpg');

-- Utilisateurs
INSERT INTO users (id, name, email, password, nom, prenom, adresse) VALUES
('1', 'Amine', 'amine@example.com', '123456', 'Manai', 'Mohamed Amine', 'Mornaguia'),
('46a4', 'manai', 'amine@test.com', 'amine6', 'manai', 'amine', 'tunis');

-- Adresses
INSERT INTO addresses (id, nom, rue, ville, code_postal, pays, user_id, is_default) VALUES
('c2f1', 'mornaguia', '04', 'mornaguia', '1110', 'tunisie', '1', true),
('44db', 'tunis', 'tunis', 'tunis', '1112', 'tunisie', '1', false);