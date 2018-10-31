<?php
    include 'connect.php';
    if(isset($_POST['action'])){
        $action=$_POST['action'];
        if(!empty($action)){
            switch ($variable) {
                case 'select':{
                    selectTour();
                }
                    break;                
                default:
                    # code...
                    break;
            }
        }
    }


    function insertTour($id,$ten,$gia){
        $sql="INSERT into tour(matour,ten,gia) values('$id','$ten','$gia')";
        return $conn->query($sql);
    }

    function selectTour(){
        $sql="SELECT * from tour";
        $res=$conn->query($sql);
        echo $res;
    }

    function updateTour(){

    }

    function delete($id){
        $sql="DELETE from Tour where id='$id'";
        $conn->query($sql);
    }
?>