//Normal function
function fun1(){
    console.log("Hello World1");
}
fun1();
// method 2
var fun2 = function(){
    console.log("hello World2");
}
fun2();
// Arrow function

var fun3=()=>{
    console.log("Hello World3");
}
fun3();
//IIEF function
(function()
{
    console.log("Hello World 4");
}());
//named function
 var fun4 = function fun4(){
    console.log("Hello World 5");

 }
 fun4();
 //With parameters - with Return values
 function fun5(a,b){
    return a+b;
 }
 var result=fun5(5,10);
 console.log(result);

 //with parameters- without return value
 function fun6(a,b){
    var res1=a*b;
    if(res1%2==0){
        console.log(res1);
    }
    else{
        console.log(res1+1);
    }
 }
 var res1=fun6(5,10);
 var res1=fun6(7,5);
// without parameters - with return value
function fun7(){
    return 10;
}
console.log(fun7());

//without parameters-with out return value
function fun8(){
    console.log("i am revanth simhadri")
}
fun8();
 
