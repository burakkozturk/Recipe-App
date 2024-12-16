package recipe_book.demo.service;

import org.springframework.stereotype.Service;
import recipe_book.demo.exceptions.ResourceNotFoundException;
import recipe_book.demo.model.Report;
import recipe_book.demo.model.ReportReason;
import recipe_book.demo.repository.ReportRepository;
import recipe_book.demo.repository.UserRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ReportService {

    private final ReportRepository reportRepository;
    private final UserRepository userRepository;

    public ReportService(ReportRepository reportRepository, UserRepository userRepository) {
        this.reportRepository = reportRepository;
        this.userRepository = userRepository;
    }

    public String createReport(Report report) {
        // Enum doğrulama
        if (report.getReason() == null || !isReasonValid(report.getReason())) {
            throw new IllegalArgumentException("Invalid reason provided.");
        }

        // CreatedAt alanını ayarla
        report.setCreatedAt(LocalDateTime.now());

        // Veritabanına kaydet
        reportRepository.save(report);

        return "Report created successfully.";
    }

    private boolean isReasonValid(ReportReason reason) {
        try {
            ReportReason.valueOf(reason.name());
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    public List<Report> getReportsByReportedId(Long reportedId) {
        return reportRepository.findByReportedId(reportedId);
    }
}
