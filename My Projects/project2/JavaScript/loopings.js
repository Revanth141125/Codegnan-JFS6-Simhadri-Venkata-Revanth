//while
var a=10;
    while(a>0){
        console.log(a)
    a--;
}
//do while
console.log("do while")

var a=10;

do{
    console.log(a)
    a--;
}
while(a>0);

console.log("for  loop");
for(var i=5;i>0;i--){
    console.log(i);
}
console.log("for in loop");

var i ={
    a : 10,
    b : 20,
    c : 30
}
for(xx in i){
    console.log(i[xx]);

}
console.log("for of loop");
var i=[10,20,30,40,50];
for(xx of i){
    console.log(xx);
}





