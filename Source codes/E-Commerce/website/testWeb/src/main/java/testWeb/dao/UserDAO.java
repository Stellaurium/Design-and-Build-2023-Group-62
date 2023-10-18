package testWeb.dao;

import testWeb.vo.*;

public interface UserDAO {
	public int queryByUserInfo (UserInfo userinfo) throws Exception;
	public String loginrobotid(UserInfo userinfo) throws Exception;
	public String returnuser(UserInfo userinfo) throws Exception;
	public String returnpassword(UserInfo userinfo) throws Exception;
}
