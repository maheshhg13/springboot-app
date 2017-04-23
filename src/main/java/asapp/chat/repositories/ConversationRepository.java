package asapp.chat.repositories;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import asapp.chat.models.Conversation;
import asapp.chat.models.User;

@Transactional
public interface ConversationRepository extends JpaRepository<Conversation, Long>{
	@Query("select c from Conversation c where (c.user1 = :user1 and c.user2 = :user2) OR (c.user1 = :user2 and c.user2 = :user1)")
	public Page<Conversation> findByUser1AndUser2(@Param("user1") User user1, @Param("user2") User user2, Pageable pageable);
}
