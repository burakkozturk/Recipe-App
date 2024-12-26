package recipe_book.demo.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import recipe_book.demo.model.Report;
import recipe_book.demo.model.ReportReason;
import recipe_book.demo.repository.ReportRepository;
import recipe_book.demo.repository.UserRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ReportService {

    private static final Logger logger = LoggerFactory.getLogger(ReportService.class);

    private final ReportRepository reportRepository;
    private final UserRepository userRepository;

    public ReportService(ReportRepository reportRepository, UserRepository userRepository) {
        this.reportRepository = reportRepository;
        this.userRepository = userRepository;
    }

    public String createReport(Report report) {
        logger.info("Creating a new report for reportedId: {}", report.getReportedId());

        // Enum doğrulama
        if (report.getReason() == null || !isReasonValid(report.getReason())) {
            logger.warn("Invalid report reason provided: {}", report.getReason());
            throw new IllegalArgumentException("Invalid reason provided.");
        }

        // CreatedAt alanını ayarla
        report.setCreatedAt(LocalDateTime.now());
        logger.debug("Report createdAt field set to: {}", report.getCreatedAt());

        // Veritabanına kaydet
        reportRepository.save(report);
        logger.info("Report successfully saved with reportedId: {}", report.getReportedId());

        return "Report created successfully.";
    }

    private boolean isReasonValid(ReportReason reason) {
        try {
            ReportReason.valueOf(reason.name());
            return true;
        } catch (IllegalArgumentException e) {
            logger.error("Invalid reason enum value: {}", reason, e);
            return false;
        }
    }

    public List<Report> getReportsByReportedId(Long reportedId) {
        logger.info("Fetching reports for reportedId: {}", reportedId);
        return reportRepository.findByReportedId(reportedId);
    }
}
