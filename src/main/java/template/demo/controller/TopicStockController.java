package template.demo.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import template.demo.dto.TopicStockCond;
import template.demo.dto.TopicStockDto;
import template.demo.entity.TopicStock;
import template.demo.repository.TopicStockRepository;

@Controller
@RequestMapping("/topic/stock")
@RequiredArgsConstructor
public class TopicStockController {
    private final TopicStockRepository topicStockRepository;

    @GetMapping("/read")
    public String  topicStockRead(Model model, TopicStockCond topicStockCondCond, Pageable pageable){
        Page<TopicStockDto> topicStocks = topicStockRepository.search(topicStockCondCond, pageable);
        model.addAttribute("topicStocks", topicStocks);
        return "topic/topicList :: #topicStock_list";
    }

    @PostMapping(value = "/create")
    public String topicStockCreate(@RequestParam("topicId") Integer topicId, @RequestParam("stockCode") String stockCode) {
        TopicStock topicStock = new TopicStock(topicId, stockCode);
        topicStockRepository.save(topicStock);
        return "redirect:/topic/read";
    }

    @PostMapping(value = "/delete")
    public String topicStockDelete(@RequestParam("topicStockId") Integer topicStockId) {
        TopicStock topicStock = topicStockRepository.findById(topicStockId).get();
        topicStock.setUseYn("N");
        topicStockRepository.save(topicStock);
        return "redirect:/topic/read";
    }

}
