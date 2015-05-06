package com.dta.beans;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;

import com.dta.entities.Article;
import com.dta.entities.Produit;
import com.dta.entities.Utilisateur;
import com.dta.metier.SearchArticle;
import com.dta.metier.SearchProduit;
import com.dta.metier.SearchUtilisateur;

@ManagedBean(name="research")
public class ResearchController {

	//user fields
	private String userName;
	private String userFirstName;
	private String userMail;
	private String userLogin;
	private String userType;

	//article fields
	private String articleName;
	private String productArticle;
	private String articleId;
	private String priceArticle;
	private String stockArticle;
	
	@EJB
	private SearchArticle searchArticle;
	
	@EJB
	private SearchProduit searchProduit;
	
	@EJB
	private SearchUtilisateur searchUtilisateur;

	private List<Article> products;
	private List<Utilisateur> users; 
	
	/*
	 * 
	 * Constructors
	 * 
	 */

	public ResearchController(){
		
		this("", "", "", "", "", "", "", "", "", "", null, null, null, null);

		List<Article> liste = new ArrayList<Article>();
		products = liste;
		users = new ArrayList<Utilisateur>();
	}
	

	public ResearchController(String userName, String userFirstName,
			String userMail, String userLogin, String userType,
			String articleName, String productArticle, String articleId,
			String priceArticle, String stockArticle,
			SearchArticle searchArticle, SearchProduit searchProduit,
			List<Article> products, List<Utilisateur> users) {
		super();
		this.userName = userName;
		this.userFirstName = userFirstName;
		this.userMail = userMail;
		this.userLogin = userLogin;
		this.userType = userType;
		this.articleName = articleName;
		this.productArticle = productArticle;
		this.articleId = articleId;
		this.priceArticle = priceArticle;
		this.stockArticle = stockArticle;
		this.searchArticle = searchArticle;
		this.searchProduit = searchProduit;
		this.products = products;
		this.users = users;
	}



	/*
	 * 
	 * Controller Methods
	 * 
	 */
	
	public void deleteUser(int id){
		System.out.println("deleteting "+id);
	}
	
	public void submitResearchArticle() {
		
		System.out.println();

		//requetes recherche
		if(!this.articleId.equals("")){
			int articleId = Integer.parseInt(this.articleId);
			products = mockRequest(articleId);
		}else{
			// create a model article to search, based on the fields search
			
			Article modelArticle = new Article();

			modelArticle.setNom( (this.articleName.equals("")) ? null : this.articleName);
			modelArticle.setPrix( (this.priceArticle.equals("")) ? -1 : Float.parseFloat(this.priceArticle));
			modelArticle.setStock( (this.stockArticle.equals("")) ? -1 : Integer.parseInt(this.stockArticle));
			
			System.out.println(modelArticle);

			products = searchArticle.findDetail(modelArticle, this.productArticle);
		}
	}
	
	public void submitResearchAllArticle(){
		products = searchArticle.findAll();
	}

	public void submitResearchUser(){
		Utilisateur modelUtilisateur = new Utilisateur();
		modelUtilisateur.setLogin(this.userLogin.equals("") ? null : this.userLogin);
		modelUtilisateur.setNom(this.userName.equals("") ? null : this.userName);
		modelUtilisateur.setPrenom(this.userFirstName.equals("") ? null : this.userFirstName);
		modelUtilisateur.setEmail(this.userMail.equals("") ? null : this.userMail);
		//modelUtilisateur.setTypeUtil(this.userType.equals("") ? null : this.userType.substring(0, 1));

		users = searchUtilisateur.findDetail(modelUtilisateur);
	}
	
	public void submitResearchAllUser(){
		users = searchUtilisateur.findAll();
	}

	public int getResultSize(){
		return products.size();
	}
	
	public String getPath(){
		return "detailProduit.xhtml";
	}
	
	/*
	 * Mock methods
	 * 
	 */

	private List<Article> mockRequest(int id){

		List<Article> result = new ArrayList<Article>();
		Article art = new Article();

		art.setNom("article n°"+id);
		art.setPrix(500);
		art.setProduit(null);
		art.setStock(100);
		result.add(art);

		return result;
	}

	private List<Utilisateur> mockRequest(String name){

		List<Utilisateur> result = new ArrayList<Utilisateur>();

		for(int i=0; i<50; i++){
			Utilisateur ut = new Utilisateur();
			
			ut.setNom(name);
			ut.setPrenom("bob");
			ut.setEmail("bob@gmail.com");
			ut.setLogin("boby");
			ut.setTitre("bricoleur");
			ut.setUtilisateurId(i);

			result.add(ut);
		}
		return result;
	}


	/*
	 * 
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ResearchController [userName=" + userName + ", userFirstName="
				+ userFirstName + ", userMail=" + userMail + ", userLogin="
				+ userLogin + ", userType=" + userType + ", articleName="
				+ articleName + ", productArticle=" + productArticle
				+ ", articleId=" + articleId + ", priceArticle=" + priceArticle
				+ ", stockArticle=" + stockArticle + ", searchArticle="
				+ searchArticle + ", searchProduit=" + searchProduit
				+ ", products=" + products + "]";
	}
	
	
	/*
	 * Getters and Setters
	 * 
	 */
	public String getUserName() {
		return userName;
	}


	public void setUserName(String userName) {
		this.userName = userName;
	}


	public String getUserFirstName() {
		return userFirstName;
	}


	public void setUserFirstName(String userFirstName) {
		this.userFirstName = userFirstName;
	}


	public String getUserMail() {
		return userMail;
	}


	public void setUserMail(String userMail) {
		this.userMail = userMail;
	}


	public String getUserLogin() {
		return userLogin;
	}


	public void setUserLogin(String userLogin) {
		this.userLogin = userLogin;
	}


	public String getUserType() {
		return userType;
	}


	public void setUserType(String userType) {
		this.userType = userType;
	}


	public String getArticleName() {
		return articleName;
	}


	public void setArticleName(String articleName) {
		this.articleName = articleName;
	}


	public String getProductArticle() {
		return productArticle;
	}


	public void setProductArticle(String productArticle) {
		this.productArticle = productArticle;
	}


	public String getArticleId() {
		return articleId;
	}


	public void setArticleId(String articleId) {
		this.articleId = articleId;
	}


	public String getPriceArticle() {
		return priceArticle;
	}


	public void setPriceArticle(String priceArticle) {
		this.priceArticle = priceArticle;
	}


	public String getStockArticle() {
		return stockArticle;
	}


	public void setStockArticle(String stockArticle) {
		this.stockArticle = stockArticle;
	}


	public SearchArticle getSearchArticle() {
		return searchArticle;
	}


	public void setSearchArticle(SearchArticle searchArticle) {
		this.searchArticle = searchArticle;
	}


	public SearchProduit getSearchProduit() {
		return searchProduit;
	}


	public void setSearchProduit(SearchProduit searchProduit) {
		this.searchProduit = searchProduit;
	}


	public List<Article> getProducts() {
		return products;
	}


	public void setProducts(List<Article> products) {
		this.products = products;
	}


	public List<Utilisateur> getUsers() {
		return users;
	}


	public void setUsers(List<Utilisateur> users) {
		this.users = users;
	}
}
