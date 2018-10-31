<?php
    define('IP','192.168.43.55');
    define('PORT','8080');

    define('ADDRESS','http://' . IP . ':' .PORT . '/' . 'web_QLTourDuLich_php/tour_dulich/');
    $servername='localhost';
    $username='root';
    $password='';
    $dbName='ql_tourdulich';

    $conn=new mysqli($servername,$username,$password,$dbName);
    $conn->set_charset('utf8');
    if($conn->connect_error){
        $res=true;
    }
    else{
        $res=false;
    }

?>