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

insert into Game (id, name, line_id, started, ended, totalScore) values (1, 'First game', 1, CURRENT_TIMESTAMP,
CURRENT_TIMESTAMP+0.123456789, 382);
insert into Game (id, name, line_id, started, ended, totalScore) values (2, 'Crazy game', 11, CURRENT_TIMESTAMP,
CURRENT_TIMESTAMP+0.78135, 221);
insert into Game (id, name, line_id, started, ended, totalScore) values (3, 'Kids corner', 5, CURRENT_TIMESTAMP,
CURRENT_TIMESTAMP+0.8887, 0);
insert into Game (id, name, line_id, started, ended, totalScore) values (4, 'Sloths', 7, CURRENT_TIMESTAMP,
CURRENT_TIMESTAMP+0.4, 0);
insert into Game (id, name, line_id) values (5, 'Second game', 1);
insert into Game (id, name, line_id) values (6, 'Kekeroni game', 11);
insert into Game (id, name, line_id) values (7, 'Flash winners', 5);
insert into Game (id, name, line_id) values (8, 'King of the DUCKS', 7);

insert into Player (id, name, game_id, maxscore) values (1, 'david', 1, 0);
insert into Player (id, name, game_id, maxscore) values (2, 'jan', 1, 148);
insert into Player (id, name, game_id, maxscore) values (3, 'tomas', 1, 234);
insert into Player (id, name, game_id) values (4, 'petr', 1);

insert into Frame (id, player_id, frame_index, roll_index, roll_one, roll_two)values (1, 1, 0, 5, 3, 2);
insert into Frame (id, player_id, frame_index, roll_index, roll_one, roll_two)values (2, 1, 1, 5, 1, 4);
insert into Frame (id, player_id, frame_index, roll_index, roll_one, roll_two)values (3, 1, 2, 2, 1, 1);
insert into Frame (id, player_id, frame_index, roll_index, roll_one, roll_two)values (4, 1, 3, 3, 1, 2);
insert into Frame (id, player_id, frame_index, roll_index, roll_one, roll_two)values (5, 1, 4, 3, 2, 1);
insert into Frame (id, player_id, frame_index, roll_index, roll_one, roll_two)values (6, 1, 5, 4, 2, 2);
insert into Frame (id, player_id, frame_index, roll_index, roll_one, roll_two)values (7, 1, 6, 4, 1, 3);
insert into Frame (id, player_id, frame_index, roll_index, roll_one, roll_two)values (8, 1, 7, 5, 1, 4);
insert into Frame (id, player_id, frame_index, roll_index, roll_one, roll_two)values (9, 1, 8, 5, 4, 1);
insert into Frame (id, player_id, frame_index, roll_index, roll_one, roll_two)values (10, 1, 9, 5, 3, 2);

insert into Frame (id, player_id, frame_index, roll_index, roll_one, roll_two)values (11, 2, 0, 8, 4, 4);
insert into Frame (id, player_id, frame_index, roll_index, roll_one, roll_two)values (12, 2, 1, 4, 1, 3);
insert into Frame (id, player_id, frame_index, roll_index, roll_one, roll_two)values (13, 2, 2, 2, 1, 1);
insert into Frame (id, player_id, frame_index, roll_index, roll_one, roll_two)values (14, 2, 3, 6, 3, 3);
insert into Frame (id, player_id, frame_index, roll_index, roll_one, roll_two)values (15, 2, 4, 3, 1, 2);
insert into Frame (id, player_id, frame_index, roll_index, roll_one, roll_two)values (16, 2, 5, 4, 1, 3);
insert into Frame (id, player_id, frame_index, roll_index, roll_one, roll_two)values (17, 2, 6, 1, 1, 0);
insert into Frame (id, player_id, frame_index, roll_index, roll_one, roll_two)values (18, 2, 7, 7, 4, 3);
insert into Frame (id, player_id, frame_index, roll_index, roll_one, roll_two)values (19, 2, 8, 5, 2, 3);
insert into Frame (id, player_id, frame_index, roll_index, roll_one, roll_two)values (20, 2, 9, 4, 3, 1);