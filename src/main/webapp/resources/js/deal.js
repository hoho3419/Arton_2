
/* 예매하기 버튼 클릭 시 실행*/
function booking(){

    /* 로그인 여부 및 ID 확인 */
    let isLogin = document.getElementsByClassName('my_img');
    let userId = isLogin[0].getAttribute('id');
    // let uri = window.location.search;
    let perfCode = new URLSearchParams(location.search).get('perfCode');

    if(null == userId || userId.trim().length == 0){
        alert("로그인 후 이용가능합니다.");
        return false;
    }

    /* 날짜, 회차, 좌석 모두 선택 시에만 예매 가능 (날짜, 회차 재선택 시에는 좌석의 radio 버튼이 모두 풀리도록 설계 해 두었음. */
    else if (document.querySelector('input[type="radio"][name="perf_seat"]:checked') == null) {
        alert("날짜, 회차, 좌석을 선택해주세요.");
        return false;
    }

    /* 예매 진행 */
    else {
        /* 예매 진행 프롬프트 */
        let ticket_num = +prompt("선택하신 등급의 티켓 개수를 입력해주세요. 최대 개수는 4장 입니다");
        let seatInvtId;
        let payMethod = "카드 결제";

        if(ticket_num > 0 && ticket_num < 5){
            let seat = document.getElementsByClassName("choice_btn3 btn_chk");
            seatInvtId = seat[0].dataset.seatcode;
        }else if(ticket_num>=5){
            alert("한번에 최대 4장 까지 예매 가능합니다.")
            return false;
        }else {
            alert("취소하였습니다.")
            return false;
        }
        let confirm_msg = confirm("예매하시겠습니까?")

        /* 보낼 data */
        let data = {
            ticketCnt : ticket_num,
            seatInvtId : seatInvtId,
            payMethod : payMethod,
            perfCode : perfCode
        }

        /* confirm_msg 확인 시 예매 과정 시작 */
        let list;
        if(confirm_msg){
            /* 예매 - Ajax 시작 **************************************************************************/
            $.ajax({
                url: '/app/deal/book',
                type: 'post',
                contentType: 'application/json',
                data: JSON.stringify(data),

                success: function (data) {
                    console.log(data)
                    let result = data.result;
                    list = data.seatStocks;

                    if (result == 0) {
                        alert("예매하지 못하였습니다. 남은 좌석수를 확인해주세요.")
                    } else if (result == 1) {
                        alert("예매가 완료되었습니다.");
                    } else {
                        alert("예매 중 문제가 생겼습니다. 관리자에게 문의해주세요.")
                    }
                },
                error: function (request, status, error) {
                    // alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
                    alert("에러.")
                },
                /* 성공 or 실패 이후 잔여석 갱신 */
                complete: function () {
                        console.log(list.length)
                    for (let i = 0; i < list.length; i++) {
                        let spanId = "seatSpan"+list[i].seatInvtId;
                        console.log(spanId)
                        console.log(list[i].seatStock)
                        document.getElementById(spanId).innerText = list[i].seatStock;
                    }
                }
            }); /* 예매 - Ajax 끝 **************************************************************************/
        }
        else{
            alert("예매를 취소했습니다.")
            return false;
        } /* confirm_msg 확인 시 예매 과정 시작 - 끝*/
    } /* 예매 진행 - 끝*/
} /* booking() - 끝*/



$(document).ready(function(){
    ////////////////////////////////////////////////////////////////////////////////////////////////////////
    /* 예매하기 섹션 js 기능 */
    // btn 날짜 선택 시 회차 선택 open
    $('.choice_btn').click(function() {
        $(this).siblings().removeClass('btn_chk');
        $(this).addClass('btn_chk');

        $('.choice_btn2').removeClass('btn_chk').css({
            display: 'none'
        });
        $('.seat_stock_box').css({
            display : 'none'
        });

        /** radio(perf_seat) 버튼 전부 선택 해제(중요!!) */
        $('input[name="perf_seat"]').removeAttr('checked');

        $('.seat_ment').css({
            display : 'block'
        })

        let label_time = $(this).data('labeltime');
        $('.'+ label_time).css({
            display: 'block'
        });
    } );

    // btn 회차 선택 시 좌석 선택 open
    $('.choice_btn2').click(function(){
        $(this).siblings().removeClass('btn_chk');
        $(this).addClass('btn_chk');

        $('.seat_stock_box').css({
            display : 'block'
        })
        $('.seat_ment').css({
            display : 'none'
        })

        $('.choice_btn3').removeClass('btn_chk').css({
            display: 'none'
        });

        /** radio(perf_seat) 버튼 전부 선택 해제(중요!!) */
        $('input[name="perf_seat"]').removeAttr('checked');


        let label_seat = $(this).data('labelseat');
        $('.'+ label_seat).css({
            display: 'block'
        });
    });

    // btn 좌석 선택
    $('.choice_btn3').click(function(){
        $(this).siblings().removeClass('btn_chk');
        $(this).addClass('btn_chk');

    });
    /* 예매하기 섹션 js 기능 끝 */
    ////////////////////////////////////////////////////////////////////////////////////////////////////////




    // 좋아요 likes 기능
    // $('.empty_heart').click(function(){
    //     $(this).css({
    //         display : 'none'
    //     })
    //     $('.full_heart').css({
    //         display : 'block'
    //     })
    //
    //     let tmp = true;
    //     //likes 수 + 1;
    //     let likes = $('.likes_cnt').text();
    //     likes = parseInt(likes) + 1;
    //     $('.likes_cnt').text(likes);
    //
    // });

    $('.isLiked').click(function (){
        let isLogin = document.getElementsByClassName('my_img');
        let userId = isLogin[0].getAttribute('id');
        // let uri = window.location.search;
        let perfCode = new URLSearchParams(location.search).get('perfCode');

        if(null == userId || userId.trim().length == 0){
            alert("로그인이 필요한 기능입니다.");
            return false;
        }
        let data = {
            perfCode : perfCode
        }

        /* 찜하기 - Ajax 시작 **************************************************************************/
        $.ajax({
            url: '/app/deal/like',
            type: 'post',
            contentType: 'application/json',
            data: JSON.stringify(data),
            success: function (data) {
                if (data.isLiked == 1) {
                    $('.isLiked').removeClass('show_this');
                    $('.full_heart').addClass('show_this');
                } else {
                    $('.isLiked').removeClass('show_this');
                    $('.empty_heart').addClass('show_this');
                }
                $('.likes_cnt').text(data.likeCnt);
            },
            error: function (request, status, error) {
                // alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
                alert("에러.")
            }
        }); /* 예매 - Ajax 끝 **************************************************************************/
    });




    //header, 탭바 따라다니게 하기, h_f.js 파일에서 따와서 조금 변형
    let header_height = $('.header').height();
    let main_o_top = $('.main').offset().top - header_height;
    let tab_height = $('.tabs').height();
    let tab_o_top = $('.tabs').offset().top - tab_height;

    $(window).scroll(function(){
        let header_bot = $(window).scrollTop();

        if(main_o_top <= header_bot){
            $('.header').addClass('header_event')
            $("#wrap").css({
                paddingTop: header_height
            })
        }
        else if(main_o_top >= header_bot){
            $('.header').removeClass('header_event')
            $("#wrap").css({
                paddingTop: 0
            })
        };

        if(header_bot >= tab_o_top){
            $('.header').removeClass('header_event')
            $('.tabs').addClass('tab_event')
            $('#wrap').css({
                paddingTop : tab_height
            })
        }
        else if(header_bot <= tab_o_top){
            $('.tabs').removeClass('tab_event')
            $('#wrap').css({
                paddingTop : 0
            })
        };

    });

    //상단으로 이동시켜주는 버튼 이미지 폴더에 아이콘 있음
    $(window).scroll(function(){
        if ($(this).scrollTop() > 600){
            $('.btn_gotop').css({
                display : 'block'
            });
        }
        else{
            $('.btn_gotop').css({
                display : 'none'
            });
        }
    });
    $('.btn_gotop').click(function(){
        $('html, body').animate({scrollTop:0},400);
        // return false;
    });

    //탭메뉴 클릭시 탭 변환
    $('.tab_title').click(function(){

        let tab_id = $(this).attr('data-tab');

        $('.tab_content').removeClass('tab_current');
        $("#"+tab_id).addClass('tab_current');

        $(this).children('span').css({
            width :'100%'
        });
        $(this).siblings().children('span').css({
            width : 0
        })

        $(this).css({
            color:'#008049'
        })
        $(this).siblings().css({
            color: '#000'
        })

        // $('.tab_title').removeClass('tab_border_bot');
        // $(this).addClass('tab_border_bot');

    });

    //리뷰탭 - 별점
    $('input[name="rating"]').click(function(){
        let rating_score = $(this).val();
        $('#input_rating').text(rating_score);
    });

    //리뷰탭 - 글 작성할 때 글자수 실시간 count 해주는거
    let content_cnt;
    $('#review_content').keyup(function(){
        content_cnt = $(this).val().length;
        $('#text_counter').text(content_cnt);
    });

    //리뷰 작성칸에 사용자가 마우스 클릭하면 로그인된 회원만 후기 작성 가능하다고 알람띄우기
    $('#review_content').click(function(){
        alert("로그인한 회원만 작성가능합니다.")

    });


    //리뷰에 보여질 사용자 id 뒤에 4글자만 마스킹하기
    function maskingid(userid) {
        // return userid.replace(/\s(?=\s{4})/g, "*");

        // console.log(userid);
        userid = userid.slice(0, userid.length-4);

        // console.log(userid+"****");

        return userid+"****";
    }


    let tmp_id = "asdf1234";

    let date = new Date();
    let today = `${date.getFullYear()}-${date.getMonth()+1}-${date.getDate()}`;
    let review;
    function input_rv(content, star_rate){
        //content = $('#review_content').val().trim();
        //star_rate = $('#input_rating').text();

        review = `<div class="rv_list_box">
                        <div class="rv_user_id">${maskingid(tmp_id)}</div>
                        <div class="rv_uploaded_box">
                            <div class="rv_uploaded rv_upload_date">${today}</div>
                            <div class="rv_uploaded rv_upload_content">${content}</div>
                        </div>
                        <div class="rv_right_box">
                            <div class="rv_star_rating star${star_rate}"></div>
                            <div class="rv_modify_delete">
                                <div class="rv_modify_btn">수정</div> |
                                <div class="rv_delete_btn">삭제</div>
                            </div>
                        </div>
                    </div>
                    <div class="rv_modify_box">
                        <div class="rv_modify"> 
                            <div class="rv_user_id">${tmp_id}</div>
                            <div class="rv_update_content_area">
                                <textarea name="rv_update" class="rv_update_content" maxlength="500" rows="4"></textarea>
                            </div>
                            <div class="rv_update_undo">
                                <button class="rv_btns update_btn">등록</button>
                                <button class="rv_btns undo_btn">취소</button>
                            </div>
                        </div>
                    </div>`

        $('.review_list').append(review);

    };


    //리뷰리스트에 리뷰 10개 박아넣기
    for(let i = 0; i < 5; i++){
        let tmp_content = "어쩌구 저쩌구 dddddd"+i;
        let tmp_star_rate = 5;
        input_rv(tmp_content, tmp_star_rate);
    }

    //리뷰개수 구해주기
    let rv_cnt = $('.rv_list_box').length;
    $('.rv_count').text(rv_cnt);

    //리뷰 등록하기*********************************************************
    $('#btn_submit_review').click(function(){

        content_cnt = $('#review_content').val().length;
        tmp_id = 'arton_1245';
        // let input_star_rate = $('#input_rating').text();

        if(content_cnt <= 10){
            alert("10자 이상 입력해주세요!")
        }
        else if(content_cnt >= 500){
            alert("최대 500자까지 입력 가능합니다.")
        }
        else{
            let input_content = $('#review_content').val().trim();
            let input_star_rate = $('#input_rating').text();

            if(input_star_rate == 0){
                alert("별점을 입력해주세요!")
            }
            else{
                input_rv(input_content , input_star_rate);
                alert("후기가 등록되었습니다.")
            }
            $('li[data-tab="tab_review"]').trigger("click"); // 새고할떄마다 리뷰탭 클릭해주기 귀찮아서 넣어준거

            //후기 등록하기 누르면 별점 체크된 부분이랑, 작성한 내용 초기화 해주기
            $('input[name="rating"]').prop("checked", false);
            $('#review_content').val("");
        }
    });

    //리뷰 리스트에서 리뷰 "수정" 버튼 누르면 수정할 수 있는 폼 보이게 하기
    // $('.rv_modify_btn').click(function(){
    $(document).on('click','.rv_modify_btn',function(){

        let review_text = $(this).parents('.rv_list_box').children('.rv_uploaded_box').children('.rv_upload_content').text();
        // console.log(review_text);
        $(this).parents('.rv_list_box').next('.rv_modify_box').children().children('.rv_update_content_area').children('.rv_update_content').val(review_text);

        let rv_box = $(this).parents('.rv_list_box');
        let md_box = rv_box.next('.rv_modify_box');
        $(this)
        rv_box.css({
            display : 'none'
        })
        md_box.css({
            display : 'block'
        })

    });


    //리뷰 리스트에서 리뷰 "삭제" 버튼 누르면 삭제
    // $('.rv_delete_btn').click(function(){
    $(document).on('click','.rv_delete_btn',function(){
        let rv_box = $(this).parents('.rv_list_box');
        rv_box.remove();
    });


    //"수정" 버튼 누르면 나오는 수정폼의 "등록" 버튼
    // $('.update_btn').click(function(){
    $(document).on('click','.update_btn',function(){
        // let reupload = $(this).parents('.rv_update_content').text().trim();
        let reupload = $(this).parent().prev().children('.rv_update_content').val().trim();
        // console.log(reupload);
        $(this).parents('.rv_modify_box').prev('.rv_list_box').children('.rv_uploaded_box').children('.rv_upload_content').text(reupload);
        //등록 버튼 누르면, 수정된 리뷰 잡는 것까지 됨. 이걸 디비에 저장하고 다시 리스트를 리로드 하면 된다.


        let rv_box = $(this).parents('.rv_modify_box').prev('.rv_list_box');
        let md_box = rv_box.next('.rv_modify_box');
        rv_box.css({
            display : 'flex'
        })
        md_box.css({
            display : 'none'
        })
        // 수정된 리뷰를 다시 등록하는거에 어려움을 느껴서 이건 백부분에 저장하고 다시 리로드 하는 방법으로 하는거 어떨지....
        // md_box.prev('.rv_list_box').children().children().children('.rv_upload_content').val(reupload); 
    })

    //수정 버튼 누르면 나오는 수정폼의 "취소" 버튼
    // $('.undo_btn').click(function(){
    $(document).on('click','.undo_btn',function(){
        let rv_box = $(this).parents('.rv_modify_box').prev('.rv_list_box');
        let md_box = rv_box.next('.rv_modify_box');
        rv_box.css({
            display : 'flex'
        })
        md_box.css({
            display : 'none'
        })
    });


    // 햄버거 버튼 누르면 왼쪽에 있는 사이드 바 나오가
    $(document).on('click', '.hamberg', function(){
        $('.ham_box').toggleClass('ham_active')
    });
    $('.side_hamberg').click(function(){
        $('.hamberg').trigger('click')
    })








});