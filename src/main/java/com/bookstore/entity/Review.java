package com.bookstore.entity;
// Generated 10-Feb-2024, 10:01:38 pm by Hibernate Tools 3.6.2.Final

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Review generated by hbm2java
 */
@Entity
@Table(name = "review", catalog = "bookstoredb")
public class Review implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer reviewId;
	private Customer customer;
	private Book book;
	private int rating;
	private String headline;
	private String comment;
	private Date reviewTime;

	public Review() {
	}

	public Review(Customer customer, Book book, int rating, String headline, String comment, Date reviewTime) {
		this.customer = customer;
		this.book = book;
		this.rating = rating;
		this.headline = headline;
		this.comment = comment;
		this.reviewTime = reviewTime;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "review_id", unique = true, nullable = false)
	public Integer getReviewId() {
		return this.reviewId;
	}

	public void setReviewId(Integer reviewId) {
		this.reviewId = reviewId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "customer_id", nullable = false)
	public Customer getCustomer() {
		return this.customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "book_id", nullable = false)
	public Book getBook() {
		return this.book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	@Column(name = "rating", nullable = false)
	public int getRating() {
		return this.rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	@Column(name = "headline", nullable = false, length = 128)
	public String getHeadline() {
		return this.headline;
	}

	public void setHeadline(String headline) {
		this.headline = headline;
	}

	@Column(name = "comment", nullable = false, length = 500)
	public String getComment() {
		return this.comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "review_time", nullable = false, length = 19)
	public Date getReviewTime() {
		return this.reviewTime;
	}

	public void setReviewTime(Date reviewTime) {
		this.reviewTime = reviewTime;
	}

}
