package edu.school21.cinema.services;

import edu.school21.cinema.models.Avatar;
import edu.school21.cinema.models.Film;
import edu.school21.cinema.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import java.io.*;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class StorageService {
	@Value("${avatar.upload.path}")
	private String pathAvatar;

	@Value("${images.upload.path}")
	private String pathImages;

	@Autowired
	FilmService filmService;

	@Autowired
	UserService userService;

	@Transactional
	public void uploadImage(MultipartFile multipartFile, Long id) {
		if (!multipartFile.isEmpty()) {
			BufferedOutputStream bos = null;
			String name = pathImages + multipartFile.getOriginalFilename();
			try {
				byte[] fileBytes = multipartFile.getBytes();
				bos = new BufferedOutputStream(new FileOutputStream(name));
				bos.write(fileBytes);
				String shortName = multipartFile.getOriginalFilename();
				Film film = filmService.findById(id);
				film.setPoster(shortName);
				filmService.update(film);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}


	@Transactional
	public void uploadAvatar(MultipartFile multipartFile, Long id) {
		Authentication a = SecurityContextHolder.getContext().getAuthentication();
		if (!multipartFile.isEmpty()) {
			BufferedOutputStream bos = null;
			String folderName = pathAvatar + id;
			File f = new File(folderName);
			if (!f.exists() || !f.isDirectory()) {
				f.mkdir();
			}
			String name = folderName + '/' + multipartFile.getOriginalFilename();
			try {
				byte[] fileBytes = multipartFile.getBytes();
				bos = new BufferedOutputStream(new FileOutputStream(name));
				bos.write(fileBytes);
				String shortName = multipartFile.getOriginalFilename();
				User user = userService.findUserByEmail(a.getName());
				user.setAvatar(shortName);
				userService.update(user);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public void load(Long id, String filename, HttpServletResponse response){
		String avatarPath = (id == null) ? pathImages :  pathAvatar + id;
		File imgFile = new File(avatarPath + "/" + filename);
		try (InputStream is = new FileInputStream(imgFile);
			 OutputStream os = response.getOutputStream();) {
			byte[] buffer = new byte[1024]; // пул буферов потока файлов изображений
			while (is.read(buffer) != -1) {
				os.write(buffer);
			}
			os.flush();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}

	public List<Avatar> loadAllAvatars(User user) {
		String avatarPath = pathAvatar + user.getId();
		File dir = new File(avatarPath); //path указывает на директорию
		File[] arrFiles = dir.listFiles();
		List<Avatar> lstAvatar = new ArrayList<>();
		if (arrFiles != null) {
			for (int i = 0; i < Objects.requireNonNull(arrFiles).length; i++) {
				Avatar avatar = new Avatar();
				avatar.setAvatarUrl(arrFiles[i].getName());
				avatar.setAvatarFileSize(arrFiles[i].length() / 1024);
				avatar.setAvatarMimeType(URLConnection.guessContentTypeFromName(arrFiles[i].getName()));
				lstAvatar.add(avatar);
			}
		}
		return lstAvatar;
	}
}
