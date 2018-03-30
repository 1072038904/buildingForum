package com.service.PostManage.imple;

import com.dao.AccountManage.LoginDao;
import com.dao.PostManage.PostDao;
import com.dao.UserInforManage.UserInforDao;
import com.model.*;
import com.service.PostManage.PostService;
import com.service.common.imple.BaseServiceImple;
import com.util.PageBean;
import com.util.tree.DicisionTree;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@Transactional
@Service
public class PostServiceImple extends BaseServiceImple<Post> implements PostService {
    @Autowired
    @Qualifier("loginDaoImple")
    private LoginDao loginDao;
    @Autowired
    private UserInforDao userInforDao;
    @Autowired
    @Qualifier("postDaoImple")
    private PostDao postDao;

    @Override
    public PageBean fuzzyQuerySectionNameByAccount(Account account, String key, Integer currentPage, Integer pageSize) {
        // TODO Auto-generated method stub
        PageBean pageBean = new PageBean();
        //Set Parameters totalRows
        Long totalRows = postDao.findCount(Post.class);
        pageBean.setAllRow(totalRows);
        pageBean.setCurrentPage(currentPage.intValue());
        pageBean.setPageSize(pageSize.intValue());
        //Initialize PageBean
        pageBean.init();
        //get the resultSet
        List<Post> resultList= postDao.findByPage("from Section as a where a.sectionName like '%"+key+"%'",currentPage,pageSize);
        pageBean.setList(resultList);
        return pageBean;
    }

    @Override
    public List<Post> findAdminPost(String adminname,int sectionId) {
        Account account=loginDao.getAccountByName(adminname);
        Section sec=new Section();
        sec.setId(sectionId);
        List<Post> post= postDao.findAdminPost(account,sec);
        return post;
    }

    @Override
    public void addViewNum(int postId) {
        Post post=postDao.findEntityById(postId,Post.class);
        post.setViewNum(post.getViewNum()+1);
        postDao.addViewNum(post);
    }
    @Override
    public Post recommendPost(UserInfor userInfor) {
        List<UserInfor> ple = userInforDao.findAll(UserInfor.class);


        Object[][] rawData = new Object[ple.size()][4];
        for (int i = 0; i < ple.size(); i++) {
            System.out.println("查找到了id" + ple.get(i).getAccount().getId());
            List<UserInfor> p = userInforDao.find("from UserInfor as a where a.account.id ='" + ple.get(i).getAccount().getId() + "'");
            for (UserInfor t : p) {
                Iterator<Post> iterator = t.getFavoritePosting().iterator();
                while (iterator.hasNext()) {
                    int j = 0;
                    Post post = iterator.next();
                    if(!post.isDelete()) {
                        rawData[j][0] =
                                t.getSex();
                        rawData[j][1] = t.getHobby();
                        rawData[j][2] = t.getLevel();
                        rawData[j][3] = post.getId();
                    }
                    break;
                }
            }
        }
        String[] attrNames = new String[]{"SEX", "HOBBY", "LEVEL"};
        //生成树
        Map<Object, List<DicisionTree.Sample>> samples = DicisionTree.readSamples(attrNames, rawData);
        DicisionTree.Tree decisionTree = (DicisionTree.Tree) DicisionTree.generateDecisionTree(samples, attrNames);
        Map<String, String> te = new HashMap<>();
        te.put("SEX", userInfor.getSex());
        te.put("HOBBY", userInfor.getHobby());
        te.put("LEVEL", userInfor.getLevel().toString());
        //判断用户分类
        DicisionTree.decideResult(decisionTree, te);
        List<Post> p = postDao.find("from Post as a where a.id ='" + te.get("result") + "'");
        for (Object ele : p) {
            Post p1 = (Post) ele;
            return p1;
        }
        return null;
    }
    @Override
    public void updateUserFav(UserInfor userInfor) {
        postDao.updateUserFav(userInfor);
    }

    @Override
    public void savePostContent(PostContent postContent) {
        postDao.savePostContent(postContent);
    }

    @Override
    public List<Post> getAllUserPost(Account account) {
        return postDao.getAllUserPost(account);
    }
    @Override
    public List<Post> fuzzyQueryPostByTitle(String title) {
        List <Post> resultList = postDao.find("from Post as a where a.title like '%"+title+"%'");
        return resultList;
    }
}
