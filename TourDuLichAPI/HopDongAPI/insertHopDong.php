<?php
    include '../connect.php';
    define('MALOAIKH_MACDINH','4');
    define('PHUTHU','0');
    define('GHICHU','Không có');
    define('TINHTRANG_DONHANG',1);
    if(isset($_POST['NgayDH'])&& isset($_POST['TongSoNguoi']) && isset($_POST['MaKH']) && $_POST['MaKH']){
        $maHopDong = rand ( 0 , 100000 );
        $ngayDH = $_POST['NgayDH'];
        $tongSoNguoi = $_POST['TongSoNguoi'];
        $maKH = $_POST['MaKH'];
        $insertHopDongquery = "INSERT INTO `ql_tourdulich`.`hop_dong` (`MaHopDong`, `NgayDat`, `MaKH`, `PhuThu`, `GhiChu`) 
                    VALUES ('$maHopDong', '$ngayDH', '$maKH', '".PHUTHU * $tongSoNguoi."', '".GHICHU."');";
        $conn->query($insertHopDongquery);
    }
    echo $maHopDong;
?>