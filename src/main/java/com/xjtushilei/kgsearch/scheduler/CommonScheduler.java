package com.xjtushilei.kgsearch.scheduler;

import com.xjtushilei.kgsearch.service.ESIndexService;
import com.xjtushilei.kgsearch.service.YottaService;
import org.apache.logging.log4j.core.util.datetime.FastDateFormat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

import java.io.IOException;
import java.util.Date;


//@Component
public class CommonScheduler {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    ESIndexService esService;

    @Autowired
    YottaService yottaService;

    FastDateFormat fdf = FastDateFormat.getInstance("yyyy-MM-dd HH:mm:ss");

    @Scheduled(fixedRate = 1000 * 60 * 30)
    public void esIndexTreeJob() {

        yottaService.getAllClassName().forEach(className -> {
            try {
                esService.indexTreeDara(yottaService.getAllTermNameByClassName(className));
                logger.info("success-[esIndexTreeJob Execute]" + fdf.format(new Date()));

            } catch (IOException e) {
                logger.error("error-[esIndexTreeJob Execute]" + fdf.format(new Date()) + e.getMessage(), e);
            }
        });
    }

    @Scheduled(fixedRate = 1000 * 60 * 30)
    public void esIndexFragmentsJob() {
        try {
            yottaService.getAllClassName().forEach(className -> esService.indexFragments(yottaService.getAllByClassName(className)));
            logger.info("success-[esIndexFragmentsJob Execute]" + fdf.format(new Date()));
        } catch (Exception e) {
            logger.error("error-[esIndexFragmentsJob Execute]" + fdf.format(new Date()) + e.getMessage(), e);
        }
    }
}
