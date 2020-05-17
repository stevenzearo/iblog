package app.stock.dao.jpa;

import app.stock.domain.Block;
import app.stock.domain.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author steve
 */
@Repository
public interface BlockDao extends JpaRepository<Stock, Long> {
	Block save(Block stock);

	Block getFirstByCode(String code);
}
