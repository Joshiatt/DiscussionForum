package discussion.forum.units.service;

import java.util.ArrayList;

import com.forum.units.Question;
import com.forum.units.Reply;
import com.forum.units.Upvote;
import com.forum.units.User;

public class UpvoteServiceImpl implements UpvoteService {
	public static ArrayList<Upvote> upvotes = new ArrayList<>();
	
	public Upvote addUpvote(Question question, User user) {
		if (question != null && user != null) {
			Upvote upvote = getUpvote(user, question, null);
			if (upvote != null) {
				System.out.println("You have already upvoted");
				return upvote;
			}
			upvote = addUpvote(user, question, null);
			question.increaseUpvoteCount();
			return upvote;
		}
		System.out.println("Any specified field can't be empty");
		return null;
	}
	
	public long upvoteCount(Reply reply) {
		int count = 0;
		for (Upvote upvote : upvotes) {
			if (upvote != null && (upvote.getReply() == reply)) {
				count++;
			}
		}
		return count;
	}
	
	public Upvote addUpvote(Reply reply, User user) {
		if (reply != null && user != null) {
			Upvote upvote = getUpvote(user, null, reply);
			if (upvote != null) {
				System.out.println("You have already upvoted");
				return upvote;
			}
			upvote = addUpvote(user, null, reply);
			return upvote;
		}
		System.out.println("Any specified field can't be empty");
		return null;
	}
	
	private Upvote getUpvote(User user, Question question, Reply reply) {
		for (Upvote upvote : upvotes) {

			if ((question!=null) && (upvote.getUser() == user) && (upvote.getQuestion() == question)) {		// Checking if the question is null or the user has already voted the question
				return upvote;
			}
			else if ((reply!=null) && (upvote.getUser() == user) && (upvote.getReply() == reply)) {		//  Checking if the reply is null or the user has already voted the reply
				return upvote;
			}
		}
		return null;
	}
	
	private Upvote addUpvote(User user, Question question, Reply reply) {
		Upvote upvote = new Upvote();
		upvote.setQuestion(question);
		upvote.setReply(reply);
		upvote.setUser(user);
		upvote.autoGenerateId();
		upvote.setCreated();
		upvotes.add(upvote);
		return upvote;
	}
}
