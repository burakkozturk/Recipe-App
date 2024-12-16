package recipe_book.demo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import recipe_book.demo.model.Report;
import recipe_book.demo.service.ReportService;

import java.util.List;

@RestController
@RequestMapping("/api/reports")
public class ReportController {

    private final ReportService reportService;

    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }

    @PostMapping("")
    public ResponseEntity<String> createReport(@RequestBody Report report) {
        try {
            String message = reportService.createReport(report);
            return ResponseEntity.status(HttpStatus.CREATED).body(message);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/{reportedId}")
    public ResponseEntity<List<Report>> getReportsForUser(@PathVariable Long reportedId) {
        List<Report> reports = reportService.getReportsByReportedId(reportedId);
        return ResponseEntity.ok(reports);
    }
}
