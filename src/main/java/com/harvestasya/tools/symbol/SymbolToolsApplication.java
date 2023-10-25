package com.harvestasya.tools.symbol;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;

import com.harvestasya.tools.symbol.model.FinalizedBlocks;
import com.harvestasya.tools.symbol.model.statistics.service.NodeInfo;
import com.harvestasya.tools.symbol.repository.FinalizedBlocksRepository;

@SpringBootApplication
@EnableScheduling
@Controller
public class SymbolToolsApplication {

	@Autowired
	private FinalizedBlocksRepository repository;

	// statistics-serviceのURLをアプリケーションプロパティから取得
	@Value("${symbol-tools.statistics-service.url}")
	String URL;

	public static void main(String[] args) {
		SpringApplication.run(SymbolToolsApplication.class, args);
	}

	@GetMapping("/")
	public String helloWorld(Model model) {
		System.out.println("Customers found with findAll():");
		System.out.println("-------------------------------");
		for (FinalizedBlocks finalizedBlocks : repository.findAll()) {
			System.out.println(finalizedBlocks);
			break;
		}
		System.out.println();

		model.addAttribute("message", "Hello World!!");
		return "index";
	}

	@GetMapping("/test")
	public String test(Model model) {

		System.out.println("URL:" + URL);
		RestTemplate restTemplate = new RestTemplate();
		NodeInfo[] nodeInfos = restTemplate.getForObject(URL, NodeInfo[].class);
		for (NodeInfo nodeInfo : nodeInfos) {
			System.out.println(nodeInfo.getVersion());
			System.out.println(nodeInfo.getLastAvailable());
			if (nodeInfo.getPeerStatus() != null) {
				System.out.println(nodeInfo.getPeerStatus().getIsAvailable());
			}
		}

		model.addAttribute("message", "Hello World!!");
		return "index";
	}

	//	@Override
	//	public void run(String... args) throws Exception {
	//		System.out.println("Customers found with findAll():");
	//		System.out.println("-------------------------------");
	//		for (FinalizedBlocks finalizedBlocks : repository.findAll()) {
	//			System.out.println(finalizedBlocks);
	//			break;
	//		}
	//		System.out.println();
	//
	//	}

	//    @GetMapping("/hello")
	//    public String hello(@RequestParam(value = "name", defaultValue = "World") String name) {
	//      return String.format("Hello %s!", name);
	//    }
}
