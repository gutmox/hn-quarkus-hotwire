package org.hn.service;

import java.time.Duration;
import java.time.Instant;

import javax.enterprise.context.ApplicationScoped;
import org.jboss.logging.Logger;
import io.quarkus.qute.TemplateInstance;
import io.quarkus.qute.api.CheckedTemplate;
import io.smallrye.mutiny.Multi;

@ApplicationScoped
public class HNService {

  private static final Logger LOG = Logger.getLogger(HNService.class);

  @CheckedTemplate
  public static class Templates {
      public static native TemplateInstance rankingstream();
  }

  public Multi<String> updateStream(int count, String name) {
    return Multi
            .createFrom()
            .ticks()
            .every(Duration.ofSeconds(1))
            .onItem()
            .transform(n-> {
              var event = Templates.rankingstream().data("count", Instant.now().toString()).render();
              LOG.info(event);
              return event;
            })
            .transform()
            .byTakingFirstItems(count);
  }

}
