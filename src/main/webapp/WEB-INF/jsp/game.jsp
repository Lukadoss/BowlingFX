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
    <c:forEach var="player" items="${game.players}">
        <h4>Player: ${player.name}</h4>
        <div id="buttons" class="buttons">
            Pins Hit:
            <button type="button" onclick="pinHit(0)"  class="btn btn-primary">Gutter</button>
            <button type="button" onclick="pinHit(1)"  class="btn btn-primary">1</button>
            <button type="button" onclick="pinHit(2)"  class="btn btn-primary">2</button>
            <button type="button" onclick="pinHit(3)"  class="btn btn-primary">3</button>
            <button type="button" onclick="pinHit(4)"  class="btn btn-primary">4</button>
            <button type="button" onclick="pinHit(5)"  class="btn btn-primary">5</button>
            <button type="button" onclick="pinHit(6)"  class="btn btn-primary">6</button>
            <button type="button" onclick="pinHit(7)"  class="btn btn-primary">7</button>
            <button type="button" onclick="pinHit(8)"  class="btn btn-primary">8</button>
            <button type="button" onclick="pinHit(9)"  class="btn btn-primary">9</button>
            <button type="button" onclick="pinHit(10)"  class="btn btn-primary">10</button>
        </div>
        <div id='scorecard'>
            <table id='scorecardTable' class='scorecard' cellpadding='1' cellspacing='0'>
                <tr>
                    <c:forEach var="i" begin="1" end="10">
                        <th colspan='6'>Frame ${i}</th>
                    </c:forEach>
                </tr>
                <tr>
                    <c:forEach var="i" begin="1" end="10">
                        <td colspan='3'></td>  <td id="frame${i}"colspan='3'>${player.rolls[i]}</td>
                    </c:forEach>
                </tr>
                <tr>
                    <c:forEach var="i" begin="0" end="9">
                        <td colspan='6'id="marker${i}">${player.frames[i]}</td>
                    </c:forEach>
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