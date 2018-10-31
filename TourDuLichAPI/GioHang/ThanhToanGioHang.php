<?php
    include '../connect.php';
    include '../constants.php';
    if(isset($_POST['ArrayCTTour'])){
        $result = 'ThatBai';
        $json = $_POST['ArrayCTTour'];
        $json_array = json_decode($json,true);
        foreach($json_array as $ctItem){
            $maTour = $ctItem['MaTour'];
            $maHopDong = $ctItem['MaHopDong'];
            $soLuongNguoi = $ctItem['SoLuongNguoi'];
            try{
                $insertCTHopDongQuery = "INSERT INTO `ql_tourdulich`.`ct_hopdong` (`MaTour`, `TINHTRANG`, `GhiChu`, `MaHopDong`, `SLNguoi`) 
                VALUES ('$maTour', '".TINHTRANG_DONHANG."', '".GHICHU."', '$maHopDong', $soLuongNguoi);";
                $conn->query($insertCTHopDongQuery);
                $result = $maHopDong;
            }catch (Exception $e) {
                
            }

        }
        echo $result;

    }
?>