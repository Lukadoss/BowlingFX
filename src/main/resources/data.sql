insert into Bowling (id, name) values (1, 'Duck Bowling Bar');
insert into Bowling (id, name) values (2, 'Lucky Strike');

insert into Line (id, bowling_id) values (1, 1);
insert into Line (id, bowling_id) values (2, 1);
insert into Line (id, bowling_id) values (3, 1);
insert into Line (id, bowling_id) values (4, 1);
insert into Line (id, bowling_id) values (5, 1);
insert into Line (id, bowling_id) values (6, 1);
insert into Line (id, bowling_id) values (7, 1);
insert into Line (id, bowling_id) values (8, 1);

insert into Line (id, bowling_id) values (9, 2);
insert into Line (id, bowling_id) values (10, 2);
insert into Line (id, bowling_id) values (11, 2);
insert into Line (id, bowling_id) values (12, 2);
insert into Line (id, bowling_id) values (13, 2);
insert into Line (id, bowling_id) values (14, 2);
insert into Line (id, bowling_id) values (15, 2);
insert into Line (id, bowling_id) values (16, 2);

insert into Game (id, name, line_id, started, ended) values (1, 'First game', 1, CURRENT_TIMESTAMP,
 CURRENT_TIMESTAMP+0.123456789);
insert into Game (id, name, line_id, started, ended) values (2, 'Crazy game', 11, CURRENT_TIMESTAMP,
CURRENT_TIMESTAMP+0.78135);
insert into Game (id, name, line_id, started, ended) values (3, 'Kids corner', 5, CURRENT_TIMESTAMP,
CURRENT_TIMESTAMP+0.8887);
insert into Game (id, name, line_id, started, ended) values (4, 'Sloths', 7,
CURRENT_TIMESTAMP, CURRENT_TIMESTAMP+0.4);
insert into Game (id, name, line_id) values (5, 'Second game', 1);
insert into Game (id, name, line_id) values (6, 'Kekeroni game', 11);
insert into Game (id, name, line_id) values (7, 'Flash winners', 5);
insert into Game (id, name, line_id) values (8, 'King of the DUCKS', 7);

insert into Player (id, name, game_id) values (1, 'david', 1);
insert into Player (id, name, game_id) values (2, 'jan', 1);
insert into Player (id, name, game_id) values (3, 'tomas', 1);
insert into Player (id, name, game_id) values (4, 'petr', 1);
insert into Player (id, name, game_id) values (5, 'Player 1', 2);
insert into Player (id, name, game_id) values (6, 'Player 2', 2);
insert into Player (id, name, game_id) values (7, 'Player 3', 2);
insert into Player (id, name, game_id) values (8, 'Player 4', 2);
insert into Player (id, name, game_id) values (9, 'Player 5', 2);
insert into Player (id, name, game_id) values (10, 'Striker', 2);
insert into Player (id, name, game_id) values (11, 'Player 7', 2);
insert into Player (id, name, game_id) values (12, 'Player 8', 2);
insert into Player (id, name, game_id) values (13, 'Player 1', 3);
insert into Player (id, name, game_id) values (14, 'Player 2', 3);
insert into Player (id, name, game_id) values (15, 'Player 1', 4);
insert into Player (id, name, game_id) values (16, 'Player 2', 4);
insert into Player (id, name, game_id) values (17, 'Player 3', 4);
insert into Player (id, name, game_id) values (18, 'Player 1', 5);
insert into Player (id, name, game_id) values (19, 'Player 2', 5);
insert into Player (id, name, game_id) values (20, 'Player 3', 5);
insert into Player (id, name, game_id) values (21, 'Player 1', 6);
insert into Player (id, name, game_id) values (22, 'Player 2', 6);
insert into Player (id, name, game_id) values (23, 'Player 3', 6);
insert into Player (id, name, game_id) values (24, 'Player 4', 6);
insert into Player (id, name, game_id) values (25, 'Player 5', 6);
insert into Player (id, name, game_id) values (26, 'Player 1', 7);
insert into Player (id, name, game_id) values (27, 'Player 2', 7);
insert into Player (id, name, game_id) values (28, 'Player 3', 7);
insert into Player (id, name, game_id) values (29, 'Player 4', 7);
insert into Player (id, name, game_id) values (30, 'Player 5', 7);
insert into Player (id, name, game_id) values (31, 'Player 6', 7);
insert into Player (id, name, game_id) values (32, 'Player 7', 7);
insert into Player (id, name, game_id) values (33, 'Player 8', 7);
insert into Player (id, name, game_id) values (34, 'Player 1', 8);
insert into Player (id, name, game_id) values (35, 'Player 2', 8);

insert into Frame (id, player_id, frame_index, roll_index, roll_one, roll_two) values (1, 1, 0, 0, 3, 2);
insert into Frame (id, player_id, frame_index, roll_index, roll_one, roll_two) values (2, 1, 1, 2, 1, 4);
insert into Frame (id, player_id, frame_index, roll_index, roll_one, roll_two) values (3, 1, 2, 4, 1, 1);
insert into Frame (id, player_id, frame_index, roll_index, roll_one, roll_two) values (4, 1, 3, 6, 1, 2);
insert into Frame (id, player_id, frame_index, roll_index, roll_one, roll_two) values (5, 1, 4, 8, 2, 1);
insert into Frame (id, player_id, frame_index, roll_index, roll_one, roll_two) values (6, 1, 5, 10, 2, 2);
insert into Frame (id, player_id, frame_index, roll_index, roll_one, roll_two) values (7, 1, 6, 12, 1, 3);
insert into Frame (id, player_id, frame_index, roll_index, roll_one, roll_two) values (8, 1, 7, 14, 1, 4);
insert into Frame (id, player_id, frame_index, roll_index, roll_one, roll_two) values (9, 1, 8, 16, 4, 1);
insert into Frame (id, player_id, frame_index, roll_index, roll_one, roll_two) values (10, 1, 9, 18, 3, 2);

insert into Frame (id, player_id, frame_index, roll_index, roll_one, roll_two) values (11, 2, 0, 0, 4, 4);
insert into Frame (id, player_id, frame_index, roll_index, roll_one, roll_two) values (12, 2, 1, 2, 1, 3);
insert into Frame (id, player_id, frame_index, roll_index, roll_one, roll_two) values (13, 2, 2, 4, 1, 1);
insert into Frame (id, player_id, frame_index, roll_index, roll_one, roll_two) values (14, 2, 3, 6, 3, 3);
insert into Frame (id, player_id, frame_index, roll_index, roll_one, roll_two) values (15, 2, 4, 8, 1, 2);
insert into Frame (id, player_id, frame_index, roll_index, roll_one, roll_two) values (16, 2, 5, 10, 1, 3);
insert into Frame (id, player_id, frame_index, roll_index, roll_one, roll_two) values (17, 2, 6, 12, 1, 0);
insert into Frame (id, player_id, frame_index, roll_index, roll_one, roll_two) values (18, 2, 7, 14, 4, 3);
insert into Frame (id, player_id, frame_index, roll_index, roll_one, roll_two) values (19, 2, 8, 16, 2, 3);
insert into Frame (id, player_id, frame_index, roll_index, roll_one, roll_two) values (20, 2, 9, 18, 3, 1);

insert into Frame (id, player_id, frame_index, roll_index, roll_one, roll_two) values (21, 3, 0, 0, 5, 4);
insert into Frame (id, player_id, frame_index, roll_index, roll_one, roll_two) values (22, 3, 1, 2, 5, 3);
insert into Frame (id, player_id, frame_index, roll_index, roll_one, roll_two) values (23, 3, 2, 4, 5, 1);
insert into Frame (id, player_id, frame_index, roll_index, roll_one, roll_two) values (24, 3, 3, 6, 5, 3);
insert into Frame (id, player_id, frame_index, roll_index, roll_one, roll_two) values (25, 3, 4, 8, 5, 2);
insert into Frame (id, player_id, frame_index, roll_index, roll_one, roll_two) values (26, 3, 5, 10, 1, 5);
insert into Frame (id, player_id, frame_index, roll_index, roll_one, roll_two) values (27, 3, 6, 12, 1, 5);
insert into Frame (id, player_id, frame_index, roll_index, roll_one, roll_two) values (28, 3, 7, 14, 4, 5);
insert into Frame (id, player_id, frame_index, roll_index, roll_one, roll_two) values (29, 3, 8, 16, 2, 5);
insert into Frame (id, player_id, frame_index, roll_index, roll_one, roll_two) values (30, 3, 9, 18, 3, 5);

insert into Frame (id, player_id, frame_index, roll_index, roll_one, roll_two) values (31, 9, 0, 0, 4, 0);
insert into Frame (id, player_id, frame_index, roll_index, roll_one, roll_two) values (32, 9, 1, 2, 1, 0);
insert into Frame (id, player_id, frame_index, roll_index, roll_one, roll_two) values (33, 9, 2, 4, 1, 0);
insert into Frame (id, player_id, frame_index, roll_index, roll_one, roll_two) values (34, 9, 3, 6, 3, 0);
insert into Frame (id, player_id, frame_index, roll_index, roll_one, roll_two) values (35, 9, 4, 8, 1, 0);
insert into Frame (id, player_id, frame_index, roll_index, roll_one, roll_two) values (36, 9, 5, 10, 0, 3);
insert into Frame (id, player_id, frame_index, roll_index, roll_one, roll_two) values (37, 9, 6, 12, 0, 0);
insert into Frame (id, player_id, frame_index, roll_index, roll_one, roll_two) values (38, 9, 7, 14, 0, 3);
insert into Frame (id, player_id, frame_index, roll_index, roll_one, roll_two) values (39, 9, 8, 16, 0, 3);
insert into Frame (id, player_id, frame_index, roll_index, roll_one, roll_two) values (40, 9, 9, 18, 0, 1);

insert into Frame (id, player_id, frame_index, roll_index, roll_one, roll_two) values (41, 4, 0, 0, 1, 4);
insert into Frame (id, player_id, frame_index, roll_index, roll_one, roll_two) values (42, 4, 1, 2, 1, 3);
insert into Frame (id, player_id, frame_index, roll_index, roll_one, roll_two) values (43, 4, 2, 4, 1, 1);
insert into Frame (id, player_id, frame_index, roll_index, roll_one, roll_two) values (44, 4, 3, 6, 3, 1);
insert into Frame (id, player_id, frame_index, roll_index, roll_one, roll_two) values (45, 4, 4, 8, 1, 6);
insert into Frame (id, player_id, frame_index, roll_index, roll_one, roll_two) values (46, 4, 5, 10, 1, 8);
insert into Frame (id, player_id, frame_index, roll_index, roll_one, roll_two) values (47, 4, 6, 12, 1, 5);
insert into Frame (id, player_id, frame_index, roll_index, roll_one, roll_two) values (48, 4, 7, 14, 4, 6);
insert into Frame (id, player_id, frame_index, roll_index, roll_one, roll_two) values (49, 4, 8, 16, 2, 1);
insert into Frame (id, player_id, frame_index, roll_index, roll_one, roll_two) values (50, 4, 9, 18, 3, 1);

insert into Frame (id, player_id, frame_index, roll_index, roll_one, roll_two) values (51, 5, 0, 0, 4, 4);
insert into Frame (id, player_id, frame_index, roll_index, roll_one, roll_two) values (52, 5, 1, 2, 1, 5);
insert into Frame (id, player_id, frame_index, roll_index, roll_one, roll_two) values (53, 5, 2, 4, 1, 9);
insert into Frame (id, player_id, frame_index, roll_index, roll_one, roll_two) values (54, 5, 3, 6, 3, 5);
insert into Frame (id, player_id, frame_index, roll_index, roll_one, roll_two) values (55, 5, 4, 8, 1, 6);
insert into Frame (id, player_id, frame_index, roll_index, roll_one, roll_two) values (56, 5, 5, 10, 5, 3);
insert into Frame (id, player_id, frame_index, roll_index, roll_one, roll_two) values (57, 5, 6, 12, 9, 0);
insert into Frame (id, player_id, frame_index, roll_index, roll_one, roll_two) values (58, 5, 7, 14, 4, 3);
insert into Frame (id, player_id, frame_index, roll_index, roll_one, roll_two) values (59, 5, 8, 16, 2, 8);
insert into Frame (id, player_id, frame_index, roll_index, roll_one, roll_two) values (60, 5, 9, 18, 3, 1);

insert into Frame (id, player_id, frame_index, roll_index, roll_one, roll_two) values (61, 6, 0, 0, 4, 1);
insert into Frame (id, player_id, frame_index, roll_index, roll_one, roll_two) values (62, 6, 1, 2, 1, 0);
insert into Frame (id, player_id, frame_index, roll_index, roll_one, roll_two) values (63, 6, 2, 4, 1, 2);
insert into Frame (id, player_id, frame_index, roll_index, roll_one, roll_two) values (64, 6, 3, 6, 3, 3);
insert into Frame (id, player_id, frame_index, roll_index, roll_one, roll_two) values (65, 6, 4, 8, 1, 4);
insert into Frame (id, player_id, frame_index, roll_index, roll_one, roll_two) values (66, 6, 5, 10, 1, 5);
insert into Frame (id, player_id, frame_index, roll_index, roll_one, roll_two) values (67, 6, 6, 12, 1, 6);
insert into Frame (id, player_id, frame_index, roll_index, roll_one, roll_two) values (68, 6, 7, 14, 4, 0);
insert into Frame (id, player_id, frame_index, roll_index, roll_one, roll_two) values (69, 6, 8, 16, 2, 7);
insert into Frame (id, player_id, frame_index, roll_index, roll_one, roll_two) values (70, 6, 9, 18, 3, 3);

insert into Frame (id, player_id, frame_index, roll_index, roll_one, roll_two) values (71, 7, 0, 0, 1, 4);
insert into Frame (id, player_id, frame_index, roll_index, roll_one, roll_two) values (72, 7, 1, 2, 2, 3);
insert into Frame (id, player_id, frame_index, roll_index, roll_one, roll_two) values (73, 7, 2, 4, 3, 1);
insert into Frame (id, player_id, frame_index, roll_index, roll_one, roll_two) values (74, 7, 3, 6, 4, 3);
insert into Frame (id, player_id, frame_index, roll_index, roll_one, roll_two) values (75, 7, 4, 8, 5, 2);
insert into Frame (id, player_id, frame_index, roll_index, roll_one, roll_two) values (76, 7, 5, 10, 6, 3);
insert into Frame (id, player_id, frame_index, roll_index, roll_one, roll_two) values (77, 7, 6, 12, 7, 0);
insert into Frame (id, player_id, frame_index, roll_index, roll_one, roll_two) values (78, 7, 7, 14, 0, 3);
insert into Frame (id, player_id, frame_index, roll_index, roll_one, roll_two) values (79, 7, 8, 16, 1, 3);
insert into Frame (id, player_id, frame_index, roll_index, roll_one, roll_two) values (80, 7, 9, 18, 2, 1);

insert into Frame (id, player_id, frame_index, roll_index, roll_one, roll_two) values (81, 8, 0, 0, 4, 5);
insert into Frame (id, player_id, frame_index, roll_index, roll_one, roll_two) values (82, 8, 1, 2, 1, 4);
insert into Frame (id, player_id, frame_index, roll_index, roll_one, roll_two) values (83, 8, 2, 4, 1, 3);
insert into Frame (id, player_id, frame_index, roll_index, roll_one, roll_two) values (84, 8, 3, 6, 3, 2);
insert into Frame (id, player_id, frame_index, roll_index, roll_one, roll_two) values (85, 8, 4, 8, 1, 1);
insert into Frame (id, player_id, frame_index, roll_index, roll_one, roll_two) values (86, 8, 5, 10, 1, 0);
insert into Frame (id, player_id, frame_index, roll_index, roll_one, roll_two) values (87, 8, 6, 12, 1, 5);
insert into Frame (id, player_id, frame_index, roll_index, roll_one, roll_two) values (88, 8, 7, 14, 4, 6);
insert into Frame (id, player_id, frame_index, roll_index, roll_one, roll_two) values (89, 8, 8, 16, 2, 7);
insert into Frame (id, player_id, frame_index, roll_index, roll_one, roll_two) values (90, 8, 9, 18, 3, 2);

insert into Frame (id, player_id, frame_index, roll_index, roll_one) values (91, 10, 0, 0, 10);
insert into Frame (id, player_id, frame_index, roll_index, roll_one) values (92, 10, 1, 1, 10);
insert into Frame (id, player_id, frame_index, roll_index, roll_one) values (93, 10, 2, 2, 10);
insert into Frame (id, player_id, frame_index, roll_index, roll_one) values (94, 10, 3, 3, 10);
insert into Frame (id, player_id, frame_index, roll_index, roll_one) values (95, 10, 4, 4, 10);
insert into Frame (id, player_id, frame_index, roll_index, roll_one) values (96, 10, 5, 5, 10);
insert into Frame (id, player_id, frame_index, roll_index, roll_one) values (97, 10, 6, 6, 10);
insert into Frame (id, player_id, frame_index, roll_index, roll_one) values (98, 10, 7, 7, 10);
insert into Frame (id, player_id, frame_index, roll_index, roll_one) values (99, 10, 8, 8, 10);
insert into Frame (id, player_id, frame_index, roll_index, roll_one, roll_two) values (100, 10, 9, 9, 5, 2);