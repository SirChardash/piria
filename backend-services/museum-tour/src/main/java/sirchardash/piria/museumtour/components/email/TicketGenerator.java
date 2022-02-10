package sirchardash.piria.museumtour.components.email;

import lombok.SneakyThrows;
import org.keycloak.representations.AccessToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;
import org.xhtmlrenderer.pdf.ITextRenderer;
import sirchardash.piria.museumtour.jpa.Museum;
import sirchardash.piria.museumtour.jpa.VirtualTour;
import sirchardash.piria.museumtour.jpa.VirtualTourAttendance;

import java.io.ByteArrayOutputStream;

@Component
public class TicketGenerator {

    private final TemplateEngine templateEngine;
    private final ITextRenderer renderer = new ITextRenderer();

    @SneakyThrows
    @Autowired
    TicketGenerator() {
        ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();
        templateResolver.setSuffix(".html");
        templateResolver.setTemplateMode(TemplateMode.HTML);
        templateEngine = new TemplateEngine();
        templateEngine.setTemplateResolver(templateResolver);
    }

    @SneakyThrows
    public byte[] generatePdf(String locale,
                              AccessToken user,
                              Museum museum,
                              VirtualTour tour,
                              VirtualTourAttendance attendance) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        renderer.setDocumentFromString(generateHtml(locale, user, museum, tour, attendance));
        renderer.layout();
        renderer.createPDF(outputStream);

        outputStream.close();
        return outputStream.toByteArray();
    }

    public String generateHtml(String locale,
                               AccessToken user,
                               Museum museum,
                               VirtualTour tour,
                               VirtualTourAttendance attendance) {
        Context context = new Context();
        context.setVariable("museumName", museum.getName());
        context.setVariable("time", tour.getStartTime());
        context.setVariable("tourName", tour.getTitle());
        context.setVariable("tourDescription", tour.getDescription());
        context.setVariable("ticketId", attendance.getTicketId());
        context.setVariable("userName", user.getName());

        return templateEngine.process("ticket_template_" + locale, context);
    }

}
