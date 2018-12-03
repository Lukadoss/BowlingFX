insert into Bowling values (1);

insert into Line (id, bowling_id) values (1, 1);
insert into Line (id, bowling_id) values (2, 1);
insert into Line (id, bowling_id) values (3, 1);
insert into Line (id, bowling_id) values (4, 1);
insert into Line (id, bowling_id) values (5, 1);
insert into Line (id, bowling_id) values (6, 1);
insert into Line (id, bowling_id) values (7, 1);
insert into Line (id, bowling_id) values (8, 1);

insert into Game (id, line_id) values (1, 1);
insert into Game (id, line_id) values (2, 3);
insert into Game (id, line_id) values (3, 5);
insert into Game (id, line_id) values (4, 7);

insert into Player (id, name, game_id) values (1, 'david', 1);
insert into Player (id, name, game_id) values (2, 'jan', 1);
insert into Player (id, name, game_id) values (3, 'tomas', 1);
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