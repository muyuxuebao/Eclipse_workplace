import java.util.regex.Pattern;

public class Test {

	public static void main(String[] args) {
		System.out.println(new Test().getAlpha("低"));
	}

	/**
	* 获取首字母
	*
	* @param str
	* @return
	*/
	public String getAlpha(String str) {
		if (str == null) {
			return "#";
		}
		if (str.trim().length() == 0) {
			return "#";
		}
		char c = str.trim().substring(0, 1).charAt(0);
		// 正则表达式匹配
		Pattern pattern = Pattern.compile("^[A-Za-z]+$");
		if (pattern.matcher(c + "").matches()) {
			return (c + "").toUpperCase(); // 将小写字母转换为大写
		} else {
			return "#";
		}
	}
}
