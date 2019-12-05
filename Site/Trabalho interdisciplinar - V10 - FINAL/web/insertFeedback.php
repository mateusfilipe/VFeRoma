<?php
$idInstituto = $_GET["sel"];
$feedbackUsuario = $_GET["feedback"];

$pdo = new PDO ('mysql:host=localhost;dbname=vferoma;charset=utf8', "root", "");
$sql = "INSERT INTO `feedback`(`id_Instituto`,`feedback_Usuario`)VALUES(?,?)";
$query = $pdo->preare($sql);
$query->execute(array($idInstituto,$feedbackUsuario));
$pdo = null;
?>
