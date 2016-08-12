package yunhen.mymvpdemo.login;

import java.util.List;

import yunhen.mymvpdemo.http.BaseResponse;

/**
 * Created by dongqi on 2016/8/10.
 */
public class LoginResp extends BaseResponse {

    /**
     * id : 168
     * phone : 18310648331
     * openId : 2uCjQjmSTRJUvj4g78raIbddTKitM8Zz
     * trueName : 宫老板
     * headImg : http://dev.api.1zhanggui.cn/u/12016-07-29/579afa75a52f8.png
     * alias : HNdGXcnAr5dfsDgZBKcG4aIPkQIKMP6T
     * storeId : 0
     * createTime : 1469417911
     * admin : 1
     * access : [{"nodeId":"4","name":"sellReturnGoods","title":"销售退货"}]
     * shop : {"title":"一号掌柜","tradeId":"0","addr":"苏州街","phone":"18310648331","tradeTitle":""}
     */

    public DataBean data;
    /**
     * data : {"id":"168","phone":"18310648331","openId":"2uCjQjmSTRJUvj4g78raIbddTKitM8Zz","trueName":"宫老板","headImg":"http://dev.api.1zhanggui.cn/u/12016-07-29/579afa75a52f8.png","alias":"HNdGXcnAr5dfsDgZBKcG4aIPkQIKMP6T","storeId":"0","createTime":"1469417911","admin":"1","access":[{"nodeId":"4","name":"sellReturnGoods","title":"销售退货"}],"shop":{"title":"一号掌柜","tradeId":"0","addr":"苏州街","phone":"18310648331","tradeTitle":""}}
     * token : 7973bfe77c98e6dfe1bb3d30ccbd71f0
     */

    public String token;

    public static class DataBean {
        public String id;
        public String phone;
        public String openId;
        public String trueName;
        public String headImg;
        public String alias;
        public String storeId;
        public String createTime;
        public String admin;
        /**
         * title : 一号掌柜
         * tradeId : 0
         * addr : 苏州街
         * phone : 18310648331
         * tradeTitle :
         */

        public ShopBean shop;
        /**
         * nodeId : 4
         * name : sellReturnGoods
         * title : 销售退货
         */

        public List<AccessBean> access;

        public static class ShopBean {
            public String title;
            public String tradeId;
            public String addr;
            public String phone;
            public String tradeTitle;
        }

        public static class AccessBean {
            public String nodeId;
            public String name;
            public String title;
        }
    }
}
