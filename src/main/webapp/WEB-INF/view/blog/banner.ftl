<div class="banner">
    <div id="slide-holder">
        <!-- 滚动图片 -->
        <div id="slide-runner">
            <a href="${base}/blog/article_list.ftl?type=1&current=1" title="程序员小说">
                <img style="left: -3000px;" id="slide-img-1" src="${resRoot}/img/common/banner_new_1.jpg" alt="程序员小说"/>
            </a>
            <a href="${base}/question/question_list.ftl?current=1" title="有问必答">
                <img style="left: -2000px;" id="slide-img-2" src="${resRoot}/img/common/banner_new_2.jpg" alt="有问必答"/>
            </a>
            <a href="${base}/message/message_list.ftl?current=1" title="留言板">
                <img style="left: -1000px;" id="slide-img-3" src="${resRoot}/img/common/banner_new_3.jpg" alt="留言板"/>
            </a>
            <a href="${base}/record/record_list.ftl?current=1" title="问题记录">
                <img style="left: 0px;" id="slide-img-4" src="${resRoot}/img/common/banner_new_4.jpg" alt="问题记录"/>
            </a>

            <div id="slide-controls">
                <p id="slide-client" class="text"><strong></strong><span></span></p>

                <p id="slide-desc" class="text"></p>

                <p id="slide-nav"></p>
            </div>
        </div>
    </div>
    <!-- 滚动图片切换 -->
    <script>
        if (!window.slider) {
            var slider = {};
        }
        slider.data = [
            {
                "id": "slide-img-1",
                "client": "",
                "desc": ""
            },
            {
                "id": "slide-img-2",
                "client": "",
                "desc": ""
            },
            {
                "id": "slide-img-3",
                "client": "",
                "desc": ""
            },
            {
                "id": "slide-img-4",
                "client": "",
                "desc": ""
            }
        ];
    </script>
</div>