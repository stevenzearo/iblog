package app.user.domain;

import javax.persistence.Enumerated;

/**
 * @author steve
 */
public enum Authority {
    @Enumerated
    ALL,
    @Enumerated
    BO_SITE,
    @Enumerated
    FRONT_SITE
}
