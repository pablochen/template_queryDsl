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
import template.demo.dto.TopicCond;
import template.demo.dto.TopicDto;
import template.demo.dto.TopicStockCond;
import template.demo.entity.Topic;
import template.demo.repository.TopicRepository;
import template.demo.repository.TopicStockRepository;

@Controller
@RequestMapping("/topic")
@RequiredArgsConstructor
public class TopicController {

    private final TopicRepository topicRepository;
    private final TopicStockRepository topicStockRepository;

    @GetMapping("/read")
    public String topicRead(Model model, TopicCond topicCond, TopicStockCond topicStockCond, Pageable pageable) {
        Page<TopicDto> topics = topicRepository.search(topicCond, pageable);
        model.addAttribute("topics", topics);
        return "topic/topicList";
    }

    @PostMapping(value = "/create")
    public String topicCreate(@RequestParam("topicName") String topicName) {
        Topic topic = new Topic(topicName);
        topicRepository.save(topic);
        return "redirect:/topic/read";
    }

    @PostMapping(value = "/delete")
    public String topicDelete(@RequestParam("topicId") int topicId) {
        Topic topic = topicRepository.findById(topicId).get();
        topic.setUseYn("N");
        topicRepository.save(topic);
        return "redirect:/topic/read";
    }





}
