console.log('restcontroller.js');

// rest3get()
function rest3get(){  console.log('rest3get');
    // 1. ajax 옵션객체 정의 
    let option = {
        //url : "http://localhost:8080/example/rest3?key=qwe" ,  // 통신할 URL , 스프링의 컨트롤러 매핑 주소 
        url : "/example/rest3?key=qwe" ,  // IP와 PORT 생략 
        method : 'get' ,    // 통신할 HTTP 메소드 선택 
        success : function(response){  // 통신 성공시 응답받은 데이터 
            console.log( response );
        } 
    }
    // 2. ajax 메소드에 옵션 넣어서 실행 
    $.ajax( option );
}

// rest3post()
function rest3post(){  console.log('rest3post');

    $.ajax( { 
        method : "post" ,                   // HTTP 메소드 
        url : "/example/rest3?key=qwe" ,    // HTTP 통신할 경로URL , 컨트롤러 매핑 
        success : function( response ){     // HTTP 성공응답 , 컨트롤러가 리턴한 return 값 
            console.log( response )
        }
    } ) // ajax end 

} // method end 

// rest3put()
function rest3put(){ console.log('rest3put');
    
    let value = document.querySelector('#value').value

    $.ajax( {
        method : 'put' ,
        url : "/example/rest3" , 
        data : { 'key' : value } ,      // data : { key : value , key : value }
        success : function (response){
            console.log( response )
        }
    } ) // ajax end 

}
// rest3delete()
function rest3delete(){  console.log('rest3delete');

    let value = document.querySelector('#value').value

    $.ajax( { 
        method : 'delete' , 
        url : "/example/rest3" , 
        data : { 'key' : value } , 
        success : function ( response ){
            console.log( response )
        }
    })

}
