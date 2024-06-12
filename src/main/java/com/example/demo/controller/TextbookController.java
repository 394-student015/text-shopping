package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entity.Lesson;
import com.example.demo.entity.Professor;
import com.example.demo.entity.Textbook;
import com.example.demo.repository.LessonRepository;
import com.example.demo.repository.ProfessorRepository;
import com.example.demo.repository.TextRepository;

@Controller
public class TextbookController {

	@Autowired
	TextRepository textRepository;

	@Autowired
	ProfessorRepository professorRepository;

	@Autowired
	LessonRepository lessonRepository;

	//教科書画面表示一覧
	@GetMapping("/textbook")
	public String textbook(Model model) {
		List<Textbook> textbookList = textRepository.findAll();
		model.addAttribute("textbook", textbookList);
		return "textbook";
	}

	//教科書登録画面
	@GetMapping("/textbook/add")
	public String textbookAdd(Model model) {

		//空のインスタンス化する
		Textbook textbook = new Textbook();
		//以下は入力内容の保持のため
		model.addAttribute("textbook", textbook);

		return "textbookAdd";
	}

	//教科書新規登録
	@PostMapping("/textbook/add")
	public String addTextbook(
			@RequestParam(value = "title", defaultValue = "") String title,
			@RequestParam(value = "author", defaultValue = "") String author,
			@RequestParam(value = "price", defaultValue = "") Integer price,
			@RequestParam(value = "stock", defaultValue = "") Integer stock,
			@RequestParam(value = "professorId", defaultValue = "") Integer professorId,
			@RequestParam(value = "lessonId", defaultValue = "") Integer lessonId,
			Model model) {

		//エラーチェック、ここから
		List<String> messages = new ArrayList<String>();
		if (title == null || title.length() == 0) {
			messages.add("書名は必須です");
		}
		if (author == null || author.length() == 0) {
			messages.add("著者名は必須です");
		}

		if (price == null) {
			messages.add("価格は必須です");
		} else if (price < 0) {
			messages.add("価格は0以上の数字で入力してください");
		}

		if (stock == null) {
			messages.add("在庫は必須です");
		} else if (stock < 0) {
			messages.add("在庫は0以上の数字で入力してください");
		}

		//List<Textbook> textbookList = textRepository.findByProfessorId(professorId);
		List<Professor> professorList = professorRepository.findIdById(professorId);

		if (professorId == null) {
			messages.add("教授IDは必須です");
		} else if (professorId < 0) {
			messages.add("教授IDは0以上の数字で入力してください");
		} else if (professorList == null || professorList.size() == 0) {
			messages.add("登録されていない教授IDです");
		}

		//List<Textbook> textbookLists = textRepository.findByLessonId(lessonId);
		List<Lesson> lessonList = lessonRepository.findIdById(lessonId);

		if (lessonId == null) {
			messages.add("授業IDは必須です");
		} else if (lessonId < 0) {
			messages.add("授業IDは0以上の数字で入力してください");
		} else if (lessonList == null || lessonList.size() == 0) {
			messages.add("登録されていない授業IDです");
		}
		//エラーチェック、ここまで

		//エラー有無に関わらずインスタンス化する
		Textbook textbook = new Textbook(title, author, price, stock, professorId, lessonId);
		if (messages.size() >= 1) {
			//エラーがあった場合
			model.addAttribute("message", messages);
			//以下があることで、エラー返したときに入力したものが入力欄に残る
			model.addAttribute("textbook", textbook);
			return "textbookAdd";
		}

		textRepository.save(textbook);
		return "redirect:/textbook";

	}

	//教科書更新画面表示
	@GetMapping("/textbook/{id}/edit")
	public String textbookEdit(
			@PathVariable("id") Integer id,
			Model model) {
		Textbook textbook = textRepository.findById(id).get();
		model.addAttribute("textbook", textbook);
		return "textbookUpdate";
	}

	//教科書更新
	@PostMapping("/textbook/{id}/edit")
	public String textbookUpdate(
			@PathVariable("id") Integer id,
			@RequestParam(value = "title", defaultValue = "") String title,
			@RequestParam(value = "author", defaultValue = "") String author,
			@RequestParam(value = "price", defaultValue = "") Integer price,
			@RequestParam(value = "stock", defaultValue = "") Integer stock,
			@RequestParam(value = "professorId", defaultValue = "") Integer professorId,
			@RequestParam(value = "lessonId", defaultValue = "") Integer lessonId,
			Model model) {

		List<String> messages = new ArrayList<String>();
		if (title == null || title.length() == 0) {
			messages.add("書名は必須です");
		}
		if (author == null || author.length() == 0) {
			messages.add("著者名は必須です");
		}

		if (price == null) {
			messages.add("価格は必須です");
		} else if (price < 0) {
			messages.add("価格は0以上の数字で入力してください");
		}

		if (stock == null) {
			messages.add("在庫は必須です");
		} else if (stock < 0) {
			messages.add("在庫は0以上の数字で入力してください");
		}

		List<Textbook> textbookList = textRepository.findByProfessorId(professorId);
		if (professorId == null) {
			messages.add("教授IDは必須です");
		} else if (professorId < 0) {
			messages.add("教授IDは0以上の数字で入力してください");
		} else if (textbookList == null || textbookList.size() == 0) {
			messages.add("登録されていない教授IDです");
		}

		List<Textbook> textbookLists = textRepository.findByLessonId(lessonId);
		if (lessonId == null) {
			messages.add("授業IDは必須です");
		} else if (lessonId < 0) {
			messages.add("授業IDは0以上の数字で入力してください");
		} else if (textbookLists == null || textbookLists.size() == 0) {
			messages.add("登録されていない授業IDです");
		}

		//エラーチェック、ここまで

		//エラー有無に関わらずインスタンス化する
		Textbook textbook = new Textbook(id, title, author, price, stock, professorId, lessonId);
		if (messages.size() >= 1) {
			//エラーがあった場合
			model.addAttribute("message", messages);
			//以下があることで、エラー返したときに入力したものが入力欄に残る
			model.addAttribute("textbook", textbook);
			return "textbookAdd";
		}

		textRepository.save(textbook);
		return "redirect:/textbook";
	}

	//教科書在庫表示一覧
	@GetMapping("/stock")
	public String stock(Model model) {
		List<Textbook> textbook = textRepository.findAll();

		model.addAttribute("textbook", textbook);
		return "stock";
	}

	//教科書在庫表示
	@GetMapping("/textbook/{id}/stock")
	public String addStock(
			@PathVariable("id") Integer id,
			Model model) {

		Textbook textbook = textRepository.findById(id).get();
		model.addAttribute("textbook", textbook);
		return "stockAdd";

	}

	//教科書在庫追加確認画面
	@PostMapping("/textbook/{id}/stock")
	public String stockConfirm(
			@PathVariable("id") Integer id,
			@RequestParam(value = "title", defaultValue = "") String title,
			@RequestParam(value = "author", defaultValue = "") String author,
			@RequestParam(value = "price", defaultValue = "") Integer price,
			@RequestParam(value = "stock", defaultValue = "") Integer stock,
			@RequestParam(value = "professorId", defaultValue = "") Integer professorId,
			@RequestParam(value = "lessonId", defaultValue = "") Integer lessonId,
			Model model) {

		//エラーメッセージ表示、ここから
		List<String> messages = new ArrayList<String>();
		if (stock == null) {
			messages.add("在庫は必須です");
		} else if (stock < 0) {
			messages.add("0以上の数字で入力してください");
		}

		Textbook textbook = new Textbook(id, title, author, price, stock, professorId, lessonId);
		model.addAttribute("message", messages);
		model.addAttribute("textbook", textbook);
		if (messages.size() >= 1) {
			model.addAttribute("message", messages);
			return "stockAdd";
		}
		//ここまで

		textRepository.save(textbook);

		return "redirect:/stock";
	}

	//教科書削除
	@PostMapping("/textbook/{id}/delete")
	public String deleteTextbook(
			@PathVariable("id") Integer id,
			Model model) {
		textRepository.deleteById(id);
		return "redirect:/textbook";
	}
}
