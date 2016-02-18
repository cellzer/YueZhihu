package io.github.cellzer.yuezhihu.yuezhihu.model;

import org.litepal.crud.DataSupport;

import java.util.List;

/**
 * Created by walmand_ on 2016/2/18 0018.
 */
public class TopUser extends DataSupport {

    /**
     * error :
     * count : 30
     * topuser : [{"id":"zhang-jia-wei","agree":1839184,"name":"张佳玮","hash":"f9de84865e3e8455a09af78bfe4d1da5","avatar":"https://pic2.zhimg.com/424c70919_l.jpg","signature":"公众号：张佳玮写字的地方"},{"id":"feifeimao","agree":892735,"name":"肥肥猫","hash":"342d47c5da88fb45217f0685d261ce64","avatar":"https://pic2.zhimg.com/920f00904521f0024d5e0b2da33afbed_l.jpg","signature":"公众号搜：肥肥猫的小酒馆"},{"id":"zhu-xuan-86","agree":882540,"name":"朱炫","hash":"ec57f56165fc5d88cef8e69816b456ba","avatar":"https://pic2.zhimg.com/b144d91ec_l.jpg","signature":"闷骚而近妖                微博：大师兄_朱炫"},{"id":"ze.ran","agree":704189,"name":"mu mu","hash":"13ba78a859eaf6b9a5b27c5c56ee8419","avatar":"https://pic3.zhimg.com/b8122761a03db2d4f31ca7608f02030d_l.jpg","signature":"less is more"},{"id":"e-miao-de-nai-ba","agree":689992,"name":"恶喵的奶爸","hash":"2b81947c2821e9d2f47907a51b15e6f8","avatar":"https://pic4.zhimg.com/a71e0ad8f6fbe604a9c60ea4aad7ff1f_l.png","signature":"公众号 lxg-milk 微博@恶喵的奶爸"},{"id":"yolfilm","agree":648423,"name":"yolfilm","hash":"28bb2b6ff09a5072198351434ab2efff","avatar":"https://pic3.zhimg.com/8f47d95fe3886e95408a1a27bfcf414a_l.jpg","signature":"     (ง \u2022̀_\u2022́)ง "},{"id":"sizhuren","agree":575346,"name":"寺主人","hash":"a9767cdbcc0404778bbb0e6ddeab0eba","avatar":"https://pic2.zhimg.com/d6c4e0a2681a65382578aaf0954f4bd5_l.jpg","signature":"「女神进化论」招聘科普主编/市场专员/产品运营/插画师"},{"id":"tang-que","agree":546571,"name":"唐缺","hash":"41f58a858f5cb84ebbead1836096e48a","avatar":"https://pic1.zhimg.com/171cc15ea_l.jpg","signature":"写小说的"},{"id":"excited-vczh","agree":539757,"name":"vczh","hash":"0970f947b898ecc0ec035f9126dd4e08","avatar":"https://pic1.zhimg.com/3a6c25ac3864540e80cdef9bc2a73900_l.jpg","signature":"专业造轮子，http://www.gaclib.net"},{"id":"yixiao-feng-yun-guo","agree":534233,"name":"一笑风云过","hash":"052982d06defa3f9ad6703c92db0feff","avatar":"https://pic2.zhimg.com/e8b6ad13f3a7c770533bba345dca1935_l.jpg","signature":"头像即本人"},{"id":"bo-cai-28-7","agree":518372,"name":"菠菜","hash":"a1d971f2154a83f8b975093bc22698b6","avatar":"https://pic3.zhimg.com/02de5c14420471f22a02db0ae93635fb_l.jpg","signature":"微信订阅号:bocai_zhu"},{"id":"gayscript","agree":510710,"name":"徐沪生","hash":"172bdd3dc7eb563194150c423a6014d4","avatar":"https://pic3.zhimg.com/3b304ed858f27927965ff5b2d36b32ba_l.png","signature":"公众号：徐沪生；微博：我叫徐沪生"},{"id":"douzishushu","agree":502597,"name":"豆子","hash":"51b341e826b48ae6879310dde823022b","avatar":"https://pic2.zhimg.com/1630898399954b1e096aca9a8b43a87d_l.png","signature":"人民艺术家"},{"id":"kaiserwang730","agree":498073,"name":"Kaiser","hash":"59fcae62596fb6ba08f2860a5fe6f171","avatar":"https://pic3.zhimg.com/29e86654163ef3977f3f0255f90bbd5e_l.png","signature":"内涵到天亮。"},{"id":"cai-tong","agree":483032,"name":"采铜","hash":"20e911524247b63b55decfbe6080aceb","avatar":"https://pic3.zhimg.com/adc4734be3ab59da5aef0f949d0c5d6e_l.jpg","signature":"元问题求索者。公众号-采铜的铜"},{"id":"xiepanda","agree":482170,"name":"谢熊猫君","hash":"c948a6c96e21986af5d9c720334989f7","avatar":"https://pic4.zhimg.com/e7a5b32f3_l.jpg","signature":"微信个人号：xiepandajun"},{"id":"zeng-kai-87","agree":477963,"name":"Hannibal Lecter","hash":"a7f9912c21fc1cd410abb2bb88205c2b","avatar":"https://pic2.zhimg.com/edb090ac6c5c6e983a374311f059c239_l.png","signature":"采蘑菇的小姑凉，爱吃蚕豆和肝脏"},{"id":"libiubiu","agree":465553,"name":"李嫑嫑","hash":"1c3369bbd58b14ed815011effaf0895c","avatar":"https://pic2.zhimg.com/78d0d27dfe4d6a15f7224bae0086ef8d_l.jpg","signature":"转载/咨询请微信告知：li_biubiu"},{"id":"zengjiaplus","agree":421946,"name":"曾加","hash":"184d63c15edb58b42e0a02628945fa76","avatar":"https://pic1.zhimg.com/451849aade121008f8c91120348894fc_l.png","signature":"把诗意的理性献给你 / 公众号:PlusZeng"},{"id":"ma-qian-zu","agree":418513,"name":"马前卒","hash":"4561b2270a104993e2988a9ce5a1cad2","avatar":"https://pic2.zhimg.com/f9c94349996bd681d6370c5085e459c0_l.jpg","signature":"山楂糕、枣泥饼、酸奶冰棍"},{"id":"huo-zhen-bu-lu-zi-lao-ye","agree":414798,"name":"霍真布鲁兹老爷","hash":"08f08153e70669661c5e4217815aec0e","avatar":"https://pic4.zhimg.com/2feef10b47934de8aeee31721e2b2841_l.jpg","signature":"微信公众号：霍老爷的小木屋  ddz_233"},{"id":"seasee-youl","agree":406567,"name":"Seasee Youl","hash":"26f0e98f29d0b22105fceef089530915","avatar":"https://pic4.zhimg.com/4decd6a9aceae904b438aabb031f282b_l.jpg","signature":"为何中意我我这种无赖 是话你蠢还是很伟大"},{"id":"lzy135","agree":404315,"name":"Justin Lee","hash":"d6e5cea2c009196cafeb31901de85594","avatar":"https://pic4.zhimg.com/7f1da7d4d2d5963416fb44154cc0fcfb_l.jpg","signature":"乐观的悲观主义者"},{"id":"Ace1987","agree":401846,"name":"张兆杰","hash":"912d322494d39f7fb6f37b1394c9a446","avatar":"https://pic1.zhimg.com/d0720cde510bdc17a10396d8f1d187b8_l.png","signature":"公众号：张兆杰说，微信号：zhangzhaojie1987"},{"id":"meng-de-er","agree":392251,"name":"孟德尔","hash":"5b4c4c40369aa3294b09f8e4bd715f23","avatar":"https://pic2.zhimg.com/8a0f51296_l.jpg","signature":"不玩游戏的游戏迷"},{"id":"WxzxzW","agree":385132,"name":"王豖","hash":"030b64419090e8f5e6ac52957ee92193","avatar":"https://pic1.zhimg.com/292ae893b44ee9201f790a2871925a68_l.jpg","signature":"一个严肃的老艺术家；公众号：王豖（Wxz_xzW）"},{"id":"magasa","agree":360412,"name":"magasa","hash":"78dd7b7be85989df73b8b0ee1857e537","avatar":"https://pic4.zhimg.com/50e9c2601_l.jpg","signature":"微信公众号「虹膜」提供最精辟的电影洞见"},{"id":"ma-bo-yong","agree":356266,"name":"马伯庸","hash":"f35fcb19ea50373fc10f2219f4a12754","avatar":"https://pic1.zhimg.com/3af4b8cc2_l.jpg","signature":"市场营销"},{"id":"leng-zhe","agree":351906,"name":"冷哲","hash":"06f3b1c891d0d504eea8af883150b497","avatar":"https://pic1.zhimg.com/06f35b1a0_l.jpg","signature":"欢迎关注微信公众号yushuReview"},{"id":"su-fei-17","agree":339005,"name":"苏菲","hash":"acb05f758c6249ff1a1d8aac2fe2aee6","avatar":"https://pic1.zhimg.com/077008ffd4061091a1169554df021340_l.jpg","signature":"公众号：SophieinTokyo 见过四大洋和北极光的小超人"}]
     */

    private String error;
    private int count;
    /**
     * id : zhang-jia-wei
     * agree : 1839184
     * name : 张佳玮
     * hash : f9de84865e3e8455a09af78bfe4d1da5
     * avatar : https://pic2.zhimg.com/424c70919_l.jpg
     * signature : 公众号：张佳玮写字的地方
     */


    private List<TopuserEntity> topuser;

    public void setError(String error) {
        this.error = error;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public void setTopuser(List<TopuserEntity> topuser) {
        this.topuser = topuser;
    }

    public String getError() {
        return error;
    }

    public int getCount() {
        return count;
    }

    public List<TopuserEntity> getTopuser() {
        return topuser;
    }
    private int rankType ;
    //判断是按赞同数排还是关注数排
    public int getRankType() {
        return rankType;
    }

    public void setRankType(int rankType) {
        this.rankType = rankType;
    }

    public static class TopuserEntity {
        private String id;
        private int agree;
        private int follower;
        private String name;
        private String hash;
        private String avatar;
        private String signature;



        public int getFollower() {
            return follower;
        }

        public void setFollower(int follower) {
            this.follower = follower;
        }

        public void setId(String id) {

            this.id = id;
        }

        public void setAgree(int agree) {
            this.agree = agree;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setHash(String hash) {
            this.hash = hash;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }

        public void setSignature(String signature) {
            this.signature = signature;
        }

        public String getId() {
            return id;
        }

        public int getAgree() {
            return agree;
        }

        public String getName() {
            return name;
        }

        public String getHash() {
            return hash;
        }

        public String getAvatar() {
            return avatar;
        }

        public String getSignature() {
            return signature;
        }
    }
}
