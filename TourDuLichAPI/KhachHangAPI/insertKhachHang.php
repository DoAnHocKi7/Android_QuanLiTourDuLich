<?php
    include '../connect.php';
    include '../constants.php';
    if(isset($_POST['TenKH']) && isset($_POST['DiaChiKH']) && isset($_POST['EmailKH']) 
        && isset($_POST['SdtKH']) && isset($_POST['GTinhKH'])){
        $maKH = rand ( 0 , 1000000000 );
        $tenKH = $_POST['TenKH'];
        $diaChiKH = $_POST['DiaChiKH'];
        $emailKH = $_POST['EmailKH'];
        $sdtKH = $_POST['SdtKH'];
        $gioiTinhKH = $_POST['SdtKH'];
        $ngayDH = $_POST['SdtKH'];
        /*-------------them thong tin KH---------------*/
        $insertKHquery = "INSERT INTO `ql_tourdulich`.`khach_hang` (`Ma_KH`, `TenKH`, `SexKH`, `Phone`, `Email`, `DiaChi`, `LoaiKH`) 
                            VALUES ('$maKH', '$tenKH', '$gioiTinhKH', '$sdtKH', '$emailKH', '$diaChiKH', '".MALOAIKH_MACDINH."');";
        $conn->query($insertKHquery);
    }
    echo $maKH;
?>