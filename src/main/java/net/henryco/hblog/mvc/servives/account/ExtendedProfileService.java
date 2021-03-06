package net.henryco.hblog.mvc.servives.account;

import net.henryco.hblog.mvc.model.dao.account.base.BaseProfileDao;
import net.henryco.hblog.mvc.model.dao.account.priv.AuthProfileDao;
import net.henryco.hblog.mvc.model.entity.account.AuthUserProfile;
import net.henryco.hblog.mvc.model.entity.account.BaseUserProfile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.List;

import static net.henryco.hblog.configurations.WebConfiguration.USER_FILE_PATH;

/**
 * @author Henry on 18/06/17.
 */
@Service
public class ExtendedProfileService {

	private final BaseProfileDao profileDao;
	private final AuthProfileDao authDao;

	@Autowired
	public ExtendedProfileService(BaseProfileDao profileDao,
								  AuthProfileDao authDao) {
		this.profileDao = profileDao;
		this.authDao = authDao;
	}

	public void saveNewBaseUserProfile(BaseUserProfile profile, String password, String ... authorities) {

		BaseUserProfile savedProfile = profileDao.addProfile(profile);
		AuthUserProfile auth = new AuthUserProfile(savedProfile.getId(), password, authorities);
		auth.setEnabled(true);
		auth.setExpired(false);
		auth.setLocked(false);
		authDao.saveAuthUserProfile(auth);
	}

	public void updateBaseUserProfile(BaseUserProfile profile) {
		profileDao.addProfile(profile);
	}
	public void updateAuthUserProfile(AuthUserProfile profile) {
		authDao.saveAuthUserProfile(profile);
	}


	public void deleteProfile(long id) {
		try {
			new File(USER_FILE_PATH + profileDao.getProfileById(id).getUserName()).deleteOnExit();
		} catch (Exception e) {e.printStackTrace();}
		authDao.deleteAuthUserProfile(id);
		profileDao.deleteBaseUserProfile(id);
	}

	public BaseUserProfile getBaseProfile(long id) {
		return profileDao.getProfileById(id);
	}

	public void setPassword(long id, String password) {
		authDao.setPassword(id, password);
	}

	public AuthUserProfile getAuthProfile(long id) {
		return authDao.getAuthUserProfile(id);
	}

	public AuthUserProfile getAuthProfileReference(long id) {
		return authDao.getAuthUserProfileReference(id);
	}

	public boolean isBaseProfileExists(long id) {
		return profileDao.isProfileExists(id);
	}

	public boolean isAuthProfileExists(long id) {
		return authDao.isAuthUserProfileExists(id);
	}

	public BaseUserProfile getBaseProfileByNameOrEmail(String nameOrEmail) {
		return profileDao.getProfileByUserNameOrEmail(nameOrEmail);
	}

	public List<BaseUserProfile> getBaseProfiles() {
		return profileDao.getAll();
	}
}
