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

insert into Game (id, name, line_id) values (1, 'First game', 1);
insert into Game (id, name, line_id) values (2, 'Crazy game', 3);
insert into Game (id, name, line_id) values (3, 'Kids corner', 5);
insert into Game (id, name, line_id) values (4, 'King of the DUCKS', 7);

insert into Player (id, name, game_id, maxscore) values (1, 'david', 1, 0);
insert into Player (id, name, game_id, maxscore) values (2, 'jan', 1, 148);
insert into Player (id, name, game_id, maxscore) values (3, 'tomas', 1, 234);
insert into Player (id, name, game_id) values (4, 'petr', 1);

insert into Rolls (player_id, rolls) values (1, 1);
insert into Rolls (player_id, rolls) values (1, 2);
insert into Rolls (player_id, rolls) values (1, 2);
insert into Rolls (player_id, rolls) values (1, 3);
insert into Rolls (player_id, rolls) values (1, 3);
insert into Rolls (player_id, rolls) values (1, 4);
insert into Rolls (player_id, rolls) values (1, 4);
insert into Rolls (player_id, rolls) values (1, 5);
insert into Rolls (player_id, rolls) values (1, 5);
insert into Rolls (player_id, rolls) values (1, 5);

insert into Rolls (player_id, rolls) values (2, 1);
insert into Rolls (player_id, rolls) values (2, 2);
insert into Rolls (player_id, rolls) values (2, 2);
insert into Rolls (player_id, rolls) values (2, 3);
insert into Rolls (player_id, rolls) values (2, 3);
insert into Rolls (player_id, rolls) values (2, 4);
insert into Rolls (player_id, rolls) values (2, 4);
insert into Rolls (player_id, rolls) values (2, 5);
insert into Rolls (player_id, rolls) values (2, 5);
insert into Rolls (player_id, rolls) values (2, 5);