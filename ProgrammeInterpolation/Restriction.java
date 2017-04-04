class Restriction{ // restriction d'un polynome de degré 3 sur un intervalle [a,b]
	
	//constructeur
	public Restriction(Polynome3 p, double a, double b){
		if(a<b){
			this.p=p;
			this.a=a;
			this.b=b;
		}
		else{ // si a>=b, la restriction est invalide
			throw new RuntimeException("Intervalle invalide");
		}
	}
	/** retourne le debut de la restriction
	  * @return le début de la restriction
	  */
	public double debut(){
		return a;
	}
	/** retourne la fin de la restriction courante
	  * @return la fin de la restriction courante
	  */
	public double fin(){
		return b;
	}
	
	/** indique si un réel est dans la restriction courant
	  * @param un reel c
	  * @return vrai si c est dans l'intervalle de définition de la restriction courante, faux sinon
	  */
	public boolean dans(double c){
		return(a<=c && c<=b);
	}
	
	/** derive la restriction courante
	  * @return la derivée de la restriction courante
	  */
	public Restriction derive(){
		return new Restriction(p.derive(),a,b);
	}
	
	/** fait la somme de la restriction courant avec un reel
	 * @param un reel
	 * @return la somme de la restriction courante avec un réel
	 */
	public Restriction add(double d){
		return new Restriction(p.add(d),a,b);
	}
	
	/** fait le produit de la restriction courant avec un reel
	 * @param un reel
	 * @return le produit de la restriction courante avec un réel
	 */
	public Restriction mult(double d){
		return new Restriction(p.mult(d),a,b);
	}
	
	/** calcule l'intégrale de la restriction sur ses valeurs positives
	 * en supposant que le polynome de la restrction est de degré 2
	 * @return l'intégrale de la restriction sur ses valeurs positives
	 */
	public double integralPos(){
		if(p.param(3)==0){
			if(p.param(2)==0){
				if(p.param(1)==0){ // si le polynome est de degré 1
					if (p.apply(a)>0){ // si la valeur initiale est positive
						return p.integral(a,b); // on fait l'intégrale de a à b
					}
					else{ // si la valeur initiale est negative
						return 0; // on retourne 0
					}
				}
				else{ // si le polynome est de degré 2
					if (p.apply(a)>0){ // si la valeur initiale est positive
						if(dans(p.sol(0))){ // si la solution est dans l'intervalle
							return p.integral(a,p.sol(0)); // on fait l'intégrale de a à la solution
						}
						else{ // si la solution n'est pas dans l'intervalle
							return p.integral(a,b); // on fait l'intégrale de a à b
						}
					}
					else{ // si la valeur initiale est negative
						if(dans(p.sol(0))){ // si la solution est dans l'intervalle
							return p.integral(p.sol(0),b); // on fait l'intégrale de la solution à b
						}
						else{ // si la solution n'est pas dans l'intervalle
							return 0; // onb retourne 0
						}
					}
				}
			}
			else{ // si le polynome est de degré 2
				if(p.nsol()==2){ // si le polynome a 2 solutions
					if(p.apply(a)>0){ // si sa valeur initiale est positive
						if(dans(p.sol(1))){ // si la solution 1 est dans la restriction
							if(dans(p.sol(2))){ // si la solution 2 est dans la restriction
								return (p.integral(a,p.sol(1)) + p.integral(p.sol(2),b)); // on fait la somme des intégrales entra a et la première solution et entre la deuxième solution et b
							}
							else{ // si la solution 2 n'est pas dans la restriction
								return p.integral(a,p.sol(1)); // on fait l'intégrale de a à la solution 1
							}
						}
						else{ // si la solution 1 n'est pas dans la restriction
							if(dans(p.sol(2))){ // si la solution 2 est dans la restriction
								return p.integral(a,p.sol(2)); // on fait l'intégrale de a à la solution 2
							}
							else{ // si la solution 2 n'est pas dans la restriction
								return p.integral(a,b); // on fait l'intégrale de a à b
							}
						}
					}
					else{ // si la valeur initiale est négative
						if(dans(p.sol(1))){ // si la solution 1 est dans la restriction
							if(dans(p.sol(2))){ // si la solution 2 est dans la restriction
								return p.integral(p.sol(1),p.sol(2)); // on fait l'intégrale de la solution 1 à la solution 2
							}
							else{ // si la solution 2 n'est pas dans la restriction
								return p.integral(p.sol(1),b); // on fait l'intégrale de la solution 1 à b
							}
						}
						else{ // si la solution 1 n'est pas dans la restriction
							if(dans(p.sol(2))){ // si la solution 2 est dans la restriction
								return p.integral(p.sol(2),b); // on fait l'intégrale de la solution 2 à b
							}
							else{ // si la solution 2 n'est pas dans la restriction
								return 0; // on retourne 0
							}
						}
					 }
				}
				else{ // si le polynome a un ou deux solutions
					if (p.apply(a)>0){ // si la valeur initiale est positive
						return p.integral(a,b); // on fait l'intégrale de a à b
					}
					else{ // si la valeur initiale est negative
						return 0; // on retourne 0
					}
				}
			}
		}
		else{ // si le polynome est de degré 3
			throw new RuntimeException("Impossible de trouver l'intégrale positive"); // on ne peut pas trouver l'intégrale positive
		}
	}
	
	private Polynome3 p; // polynome
	private double a; // début de l'intervalle
	private double b; // fin de l'intervalle
}
