<!DOCTYPE html>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix = "fn" uri = "http://java.sun.com/jsp/jstl/functions" %>
<html lang="en">
<head>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
    <link rel="stylesheet" type="text/css" href="/css/bowling.css">
</head>
<body>

<h1 class="title">Bowling Score Card</h1>
<h2> Playing on line: ${game.line} </h2>
    <c:forEach var="player" items="${players}" varStatus="loop">
        <h4>Player: ${player.name}</h4>
        <div id="buttons" class="row">

            <form class="col-sm" method="POST" action="${game.id}/${player.id}/0">
                <button type="submit" class="btn btn-primary">
                    Gutter
                </button>
            </form>
            <form class="col-sm" method="POST" action="${game.id}/${player.id}/1">
                <button type="submit" class="btn btn-primary">
                    1
                </button>
            </form>
            <form class="col-sm" method="POST" action="${game.id}/${player.id}/2">
                <button type="submit" class="btn btn-primary">
                    2
                </button>
            </form>
            <form class="col-sm" method="POST" action="${game.id}/${player.id}/3">
                <button type="submit" class="btn btn-primary">
                    3
                </button>
            </form>
            <form class="col-sm" method="POST" action="${game.id}/${player.id}/4">
                <button type="submit" class="btn btn-primary">
                    4
                </button>
            </form>
            <form class="col-sm" method="POST" action="${game.id}/${player.id}/5">
                <button type="submit" class="btn btn-primary">
                    5
                </button>
            </form>
            <form class="col-sm" method="POST" action="${game.id}/${player.id}/6">
                <button type="submit" class="btn btn-primary">
                    6
                </button>
            </form>
            <form class="col-sm" method="POST" action="${game.id}/${player.id}/7">
                <button type="submit" class="btn btn-primary">
                    7
                </button>
            </form>
            <form class="col-sm" method="POST" action="${game.id}/${player.id}/8">
                <button type="submit" class="btn btn-primary">
                    8
                </button>
            </form>
            <form class="col-sm" method="POST" action="${game.id}/${player.id}/9">
                <button type="submit" class="btn btn-primary">
                    9
                </button>
            </form>
            <form class="col-sm" method="POST" action="${game.id}/${player.id}/10">
                <button type="submit" class="btn btn-primary">
                    10
                </button>
            </form>
        </div>
        <div id='scorecard'>
            <table id='scorecardTable' class='scorecard' cellpadding='1' cellspacing='0'>
                <tr>
                    <c:forEach var="i" begin="1" end="10">
                        <th colspan='6'>Frame ${i}</th>
                    </c:forEach>
                    <th colspan='6'>Score</th>
                </tr>
                <tr>
                    <c:forEach var="i" begin="0" end="17" step="2">
                        <td colspan='2'></td>  <td class='bordered' id="frame${i}"colspan='2'>${player.rolls[i]}</td> <td class='bordered' id="frame${i+1}"colspan='2'>${player.rolls[i+1]}</td>
                    </c:forEach>
                    <td class='bordered' id="frame${18}"colspan='2'>${player.rolls[18]}</td>  <td class='bordered' id="frame${19}"colspan='2'>${player.rolls[19]}</td>  <td class='bordered' id="frame${20}"colspan='2'>${player.rolls[20]}</td>
                    <td colspan='6'></td>
                </tr>
                <tr>
                    <c:forEach var="i" begin="0" end="9">
                        <td colspan='6'id="marker${i}">${player.frameScores[i]}</td>
                    </c:forEach>
                    <td colspan='6'>${player.score}</td>
                </tr>
            </table>
        </div>
    </c:forEach>
<h1 id="comments" class="notice"></h1>
<h1 id="gameover" class="notice"></h1>
<h1 id="yourscore" class="final-score"></h1>
<div id="playagain"></div>

<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>

</body>

</html>