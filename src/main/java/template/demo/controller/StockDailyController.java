package template.demo.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import template.demo.dto.StockDailyCond;
import template.demo.dto.StockDailyDto;
import template.demo.repository.StockDailyRepository;

@Controller
@RequestMapping("/stock/daily")
@RequiredArgsConstructor
public class StockDailyController {

    private final StockDailyRepository stockDailyRepository;

    @GetMapping("/read")
    public String stockDailyRead(Model model, StockDailyCond stockDailyCond, Pageable pageable) {
        Page<StockDailyDto> stockDailies = stockDailyRepository.search(stockDailyCond, pageable);
        model.addAttribute("stockDailies", stockDailies);
        return "topic/topicList :: #stockDaily_list";
    }
}
