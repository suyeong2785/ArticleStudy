package com.suyeong.java.ssg;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.suyeong.java.ssg.dto.Article;
import com.suyeong.java.ssg.dto.Member;

public class Main {
	public static List<Article> articles;
	public static List<Member> members;
	static {
		articles = new ArrayList<>();
		members = new ArrayList<>();
	}

	public static void main(String[] args) {
		System.out.println("== 프로그램 시작 ==");

		Scanner scanner = new Scanner(System.in);
		makeTestData();
		int lastArticleId = articles.size();

		while (true) {

			System.out.print("명령어) ");
			String command = scanner.nextLine();
			command = command.trim();

			if (command.length() == 0) {
				continue;
			}

			if (command.equals("system exit")) {
				break;
			}

			if (command.equals("article write")) {

				int id = lastArticleId + 1;
				lastArticleId = id;

				System.out.printf("번호 : %d\n", id);

				String date = Util.getNowDate();
				System.out.printf("날짜 : %s\n", date);
				System.out.printf("제목 : ");
				String title = scanner.nextLine();
				System.out.printf("내용 : ");
				String body = scanner.nextLine();

				System.out.printf("%d번 게시물이 작성되었습니다.\n", id);

				Article article = new Article(id, date, title, body);
				articles.add(article);
			}
			if (command.startsWith("article list")) {
				if (articles.size() == 0) {
					System.out.println("게시물이 존재하지 않습니다.");
					continue;
				}

				String searchWord = command.substring("article list".length()).trim();

				List<Article> foundArticle = articles;
				if (searchWord.length() > 0) {
					foundArticle = new ArrayList<>();

					for (Article article : articles) {

						if (article.title.contains(searchWord)) {

							foundArticle.add(article);

						}
					}
					if (foundArticle.size() == 0) {
						System.out.println("검색결과가 존재하지 않습니다.");

					}

				}
				System.out.println(" 번호 | 조회 | 제목 ");
				for (int i = foundArticle.size() - 1; i >= 0; i--) {

					Article article = foundArticle.get(i);
					System.out.printf("%4d | %4d | %s\n", article.id, article.hit, article.title);
				}
			}

			if (command.startsWith("article detail")) {
				if (articles.size() == 0) {
					System.out.println("게시물이 존재하지 않습니다.");
					continue;
				}

				String[] commandBits = command.split(" ");
				int id = Integer.parseInt(commandBits[2]);

				Article foundArticle = null;
				for (int i = 0; i < articles.size(); i++) {
					Article article = articles.get(i);

					if (id == article.id) {
						foundArticle = article;
					}
				}

				if (foundArticle == null) {
					System.out.println("검색하신 결과가 존재하지 않습니다.");
					continue;
				}

				System.out.printf("번호 : %d\n", foundArticle.id);
				System.out.printf("날짜 : %s\n", foundArticle.date);
				System.out.printf("제목 : %s\n", foundArticle.title);
				System.out.printf("내용 : %s\n", foundArticle.body);

			} else if (command.startsWith("article delete")) {
				if (articles.size() == 0) {
					System.out.println("게시물이 존재하지 않습니다.");
					continue;
				}

				String[] commandBits = command.split(" ");
				int id = Integer.parseInt(commandBits[2]);

				int index = -1;
				for (int i = 0; i < articles.size(); i++) {
					Article article = articles.get(i);

					if (id == article.id) {
						index = i;
					}
				}

				if (index == -1) {
					System.out.println("검색하신 결과가 존재하지 않습니다.");
					continue;
				}
				System.out.printf("%d번 게시물을 삭제하였습니다.\n", id);
				articles.remove(index);
			} else if (command.startsWith("article modify")) {
				if (articles.size() == 0) {
					System.out.println("게시물이 존재하지 않습니다.");
					continue;
				}

				String[] commandBits = command.split(" ");
				int id = Integer.parseInt(commandBits[2]);

				Article foundArticle = null;
				for (int i = 0; i < articles.size(); i++) {
					Article article = articles.get(i);

					if (id == article.id) {
						foundArticle = article;
					}
				}

				if (foundArticle == null) {
					System.out.println("검색하신 결과가 존재하지 않습니다.");
					continue;
				}
				System.out.printf("제목 : ");
				String title = scanner.nextLine();
				foundArticle.title = title;
				System.out.printf("내용 : ");
				String body = scanner.nextLine();
				foundArticle.body = body;
				System.out.printf("%d번 게시물이 수정되었습니다.\n", id);
			}

			else if (command.equals("member join")) {

				String logInId = null;
				while (true) {
					System.out.print("로그인 아이디: ");
					logInId = scanner.nextLine();

					if (isjoinable(logInId) == false) {
						System.out.println("이미 존재하는 아이디 입니다.");
						continue;
					}
					break;
				}
				String logInPassword = null;
				while (true) {
					System.out.print("로그인 비밀번호: ");
					logInPassword = scanner.nextLine();
					System.out.print("로그인 비밀번호 확인: ");

					String logInPasswordConfirm = scanner.nextLine();

					if (logInPassword.equals(logInPasswordConfirm)) {
						break;
					}
					System.out.println("일치하지 않습니다.다시 입력해 주시기 바랍니다.");
					continue;
				}
				System.out.print("이름 : ");
				String name = scanner.nextLine();

				System.out.printf("%s님 회원가입을 축하드립니다.\n", logInId);

				Member member = new Member(logInId, logInPassword, name);
				members.add(member);
			} else if (command.equals("member login")) {

				while (true) {

					System.out.print("로그인 아이디: ");
					String logInId = scanner.nextLine();
					System.out.print("로그인 비밀번호: ");
					String logInPassword = scanner.nextLine();

					if (isOkayToLogin(logInId, logInPassword) == false) {
						System.out.println("입력하신 정보와 일치하는 계정이 없습니다.다시 입력해주시기 바랍니다.");
					}
					break;
				}
			}
		}

		System.out.println("== 프로그램 끝 ==");

	}

	private static boolean isOkayToLogin(String logInId, String logInPassword) {
		
		return false;
	}

	private static boolean isjoinable(String logInId) {
		int index = getLogInByid(logInId);

		if (index == -1) {

			return true;
		}

		return false;
	}

	private static int getLogInByid(String logInId) {
		int i = 0;
		for (Member member : members) {
			if (member.logInId.equals(logInId)) {
				return i;

			}
			i++;
		}

		return -1;
	}

	private static void makeTestData() {
		System.out.println("테스트를 위한 데이터를 생성합니다.");

		articles.add(new Article(1, Util.getNowDate(), "제목1", "내용1", 1));
		articles.add(new Article(2, Util.getNowDate(), "제목2", "내용2", 22));
		articles.add(new Article(3, Util.getNowDate(), "제목3", "내용3", 333));

	}
}
