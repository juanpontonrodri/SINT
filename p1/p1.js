
function validacion(){

  document.getElementById('cbrowser').value =navigator.userAgent;
  d=new Date();
  document.getElementById('cdate').value = d;

  var correcto=new Boolean(true);
  var text1="";
  if(!check_dni()){
    text1+="DNI has wrong format. 8 digits starting with a number between 0 and 7 and additional non-accented ASCII letter (e.g., 12345678A)\n\n";

    correcto=false;
  }

  if(!check_login()){
    text1+="Login username has wrong format: between 4 and 8 characters (only ASCII lowercase letters and numbers) \n  \n";

    correcto=false;
  }

  if(!check_passwd()){

    text1+="Passwd has wrong format: between 6 and 12 characters (at least one uppercase letter, one lowercase letter (in both cases ASCII letters only, no accents), one number, and one symbol from set “+-*/” \n\n";

    correcto=false;
  }

  

  if(!correcto){
  alert(text1);
  return false;
  }
}

function check_boxes(){
  if(document.getElementById('cgenreall').checked){
    var boxes=document.getElementsByName("cgenre[]");
    
    boxes.forEach(element => {
      element.checked=true;
    });
    document.getElementById('cgenreunall').checked=false;

}}

function uncheck_boxes(){
  if(document.getElementById('cgenreunall').checked){
    var boxes=document.getElementsByName("cgenre[]");
    boxes.forEach(element => {
      element.checked=false;
    });
    document.getElementById('cgenreall').checked=false;

  }

}

function check_only() { 
  document.getElementById('cgenreunall').checked=false;
  document.getElementById('cgenreall').checked=false;
  }


function get(){
  document.getElementById('reg_form').method="get";
}


function post(){
  document.getElementById('reg_form').method="post";
    
}

function enc_appl(){
  document.getElementById('reg_form').enctype="application/x-www-form-urlencoded";

}
function enc_mult(){
  document.getElementById('reg_form').enctype="multipart/form-data";

}

function check_login() {
  if(document.getElementById('clogin').value.length == 0) return true;
		var cklogin = /^[a-z0-9]{4,8}$/;
    if (cklogin.test(document.getElementById('clogin').value)) {
      return true;
    }
    else return false;
    
}


function check_passwd() {
  if(document.getElementById('cpasswd').value.length == 0) return true;
		var ckpasswd = /(?=.*[-,+,*,/])(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{6,12}/;
    if (ckpasswd.test(document.getElementById('cpasswd').value)) {

      return true;
    }
    else {

      return false;
    }
}

function check_dni(){
  if (document.getElementById('cdni').value.length == 0) return true;
    var cdni=document.getElementById('cdni').value;
    var ckpasswd = /^[0-7]{1}[0-9]{7}[A-Za-z]{1}$/;
    if(ckpasswd.test(document.getElementById('cdni').value)){
        return true;
    }
    else return false;
}
