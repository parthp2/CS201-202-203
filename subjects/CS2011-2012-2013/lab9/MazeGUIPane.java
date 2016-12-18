package lab9;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.JOptionPane;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.stage.Stage;

public class MazeGUIPane extends BorderPane {
	
	List<Coordinate> co=new ArrayList<Coordinate>();

	public void start(final Stage primarystage)
	{
		
		
	
		BorderPane bp=new BorderPane();
		final GridPane gp=new GridPane();
		
		gp.setHgap(1.5); 
		gp.setVgap(1.5); 
		
	
	    final Scene sc=new Scene(bp,623,675);
		
		sc.getStylesheets().add("Style/Styles.css");
		gp.setStyle("-fx-background-color:#aaaaaa");
		
		Label l1=new Label("Map of Pompona");
		l1.getStyleClass().add("l1");
		l1.setTextFill(Color.LAWNGREEN);
		Button b1=new Button("Run");
		
		
		final Label result= new Label();
		result.setTextFill(Color.BLUE);
		
		HBox tbox=new HBox();
		tbox.getStyleClass().add("tbox");
		tbox.setStyle("-fx-background-color:#777");
		tbox.setAlignment(Pos.TOP_CENTER);
		tbox.getChildren().add(l1);
		
		HBox bbox=new HBox();
		bbox.setAlignment(Pos.BOTTOM_CENTER);
		bbox.getChildren().add(b1);
		bbox.getChildren().add(result);
		
		
		
		
		for(int i=0;i<25;i++) //column
		{
			for(int j=0;j<25;j++) //row
			{
				final Label b=new Label();
				//b.getStyleClass().add("b");
				b.setMinHeight(23);
				b.setMinWidth(23);
				b.setShape(new Rectangle(30,30));
			   // b.setStyle("-fx-background-color: #000;");
				
				if(i==1 && j==0)
				{
				
					b.setText("  S ");
					b.setTextFill(Color.RED);
					b.setStyle("-fx-text-fill:#00000");
					b.setStyle("-fx-background-color:#0000ff");
					gp.add(b,i, j);
					co.add(new Coordinate(i,j,'S'));
				}
				else if( i==23 && j==24 )
				{
					b.setText("   E ");
					b.setTextFill(Color.RED);
					b.setStyle("-fx-text-fill:red;-fx-font:100px;");
					b.setStyle("-fx-background-color:#0000ff");	
					gp.add(b,i, j);
					co.add(new Coordinate(i,j,'E'));
				}
				else if(i==0 || i==24)
				{
					b.setStyle("-fx-background-color:#aaaaaa");
					gp.add(b,i, j);
					co.add(new Coordinate(i,j,'W'));
				
				}
				else if(j==0 || j==24)
				{
					b.setStyle("-fx-background-color:#aaaaaa");
					gp.add(b,i, j);
					co.add(new Coordinate(i,j,'W'));
				}
				else if((i !=0 || j !=0) || (i !=24 || j!=24))
				{
					
					 Random rand = new Random();
					
					
					// row=rand.ints(1,24).findFirst().getAsInt();
					// col=rand.ints(1,24).findFirst().getAsInt();
					 int p=rand.nextInt(4);
					 
					 
					
					 gp.add(b,i,j);
					
					if(p==0 || p==2 || p==3)
					{
						b.setStyle("-fx-background-color: #000;");
						co.add(new Coordinate(i,j,'B'));
					}
					if(p==1)
					{	
						//b.setStyle("-fx-background-color:#FF0000");
						b.setStyle("-fx-background-color:#aaaaaa");
						co.add(new Coordinate(i,j,'W'));
					}
					
					 b.addEventHandler(MouseEvent.MOUSE_CLICKED,
								
								new EventHandler<Event>()
								{
						 
						 			
									boolean clicked=false;
									
									@Override
									public void handle(Event event)
									{
										if(b.getStyle()=="-fx-background-color:#aaaaaa")
										{
										
											 b.setStyle("-fx-background-color: #000;");
											 findindex(gp.getColumnIndex(b),gp.getRowIndex(b));
																		
										}
										else if(b.getStyle()=="-fx-background-color: #000;")
										{
											b.setStyle("-fx-background-color:#aaaaaa");
											findindex(gp.getColumnIndex(b),gp.getRowIndex(b));
											
										}
										
									}
									
								}
								

					);

			    }
				
				
				
			}
			
			
		}
		
		final Label f=new Label("F");
		 HBox hbox = new HBox();
		 Image image = new Image(getClass().getResourceAsStream("fool.png"),23,23,false,false);
		 f.setGraphic(new ImageView(image));
		 f.setMinHeight(23);
		 f.setMinWidth(23);
		f.setTextFill(Color.RED);
		gp.add(f,1,0);
		
		
		final Label l3=new Label("B");
		 Image image1 = new Image(getClass().getResourceAsStream("bull.png"),23,23,false,false);
		 l3.setGraphic(new ImageView(image1));
		 l3.setMinHeight(23);
		 l3.setMinWidth(23);
		l3.setTextFill(Color.RED);
		gp.add(l3,1,0);
		
	
	
		b1.setOnAction(new EventHandler<ActionEvent>() {
			
			final Coordinate c=new Coordinate(1,0,'S');
		  	  final Coordinate w=new Coordinate(1,0,'S');
		  	  int row=w.getRow();
				  int col=w.getColumn();
			
			      @Override
			
			      public void handle(ActionEvent actionEvent) {
			    	  
			    	  
	    			  
			    	  sc.setOnKeyPressed(new EventHandler<KeyEvent>()
			    			  {
			    		  @Override
						            public void handle(KeyEvent ke) 
						            {
			    			  
						                if (ke.getCode()==KeyCode.DOWN) 
						                {
						                	
						                	if(c.getColumn()>4 || c.getRow()>4 )
						                	{
						                		
						                		
						                		           col=col+1;
						                		           w.setColumn(col);;
						                	
						                			       if(findvalue(w.getColumn(),w.getRow())=='W')
						                					{
						                			    	   col=col-1;
						                			    	   w.setColumn(col);;
						                			    	   
						                			    	   
						                			    	   row=row+1;
						                			    	   w.setRow(row);
								                			       if(findvalue(w.getColumn(),w.getRow())=='W')
								                					{
								                			    	   row=row-1;
								                			    	   w.setRow(row); 
								                					}
								                			       else  
								                			       {
								                			    	   if(row==c.getRow() & col==c.getColumn())
								                			    	   {
								                			    		   result.setText("   Game over bull catches full");
								                			    		JOptionPane.showMessageDialog(null,"Game over");
								                			    		
								                			    		
								                			    	   }
								                			    	   else
								                			    	   {
														                    gp.getChildren().remove(l3);
														                	gp.add(l3, w.getRow(),w.getColumn());
									                			       }
								                			       }
								              
						                			    	   
						                					}
						                			       else 
						                			       {
						                			    	   if(row==c.getRow() & col==c.getColumn())
						                			    	   {
						                			    		   result.setText("   Game over bull catches full");
						                			    	JOptionPane.showMessageDialog(null,"Game over");


						                			    	   }
						                			    	   else
						                			    	   {
												                    gp.getChildren().remove(l3);
												                	gp.add(l3, w.getRow(),w.getColumn());
						                			    		   
						                			    	   }
						                			       }
						                			  
						                			   
						                	}
						                	
						                	
						                	if(findvalue(c.getColumn()+1,c.getRow())=='W')
						                	{
						                		
						                	}
						                	else if(findvalue(c.getColumn(),c.getRow())=='E')
						                	{
						                		result.setText("  Congratulations You win the game !");
						                		JOptionPane.showMessageDialog(null, "Congratulations You win the game !");
						                		
						                	}
						                	else
						                	{
						                	c.setColumn(c.getColumn()+1);
						                    gp.getChildren().remove(f);
						                	gp.add(f, c.getRow(),c.getColumn());
						                	}
						                    
						                }
						                else  if (ke.getCode()==KeyCode.UP) 
						                {
						                	
						                	if(c.getColumn()>4 || c.getRow()>4 )
						                	{
						                		
						                		
						                		           col=col-1;
						                		           w.setColumn(col);;
						                			       
						                			       if(findvalue(w.getColumn(),w.getRow())=='W')
						                					{
						                			    	   col=col+1;
						                			    	   w.setColumn(col);;
						                			    	  
						                			    	   
						                			    	   row=row-1;
						                			    	   w.setRow(row);
								                			       if(findvalue(w.getColumn(),w.getRow())=='W')
								                					{
								                			    	   row=row+1;
								                			    	   w.setRow(row);
								                			    	   
								                					}
								                			       else  
								                			       {
								                			    	   if(row==c.getRow() & col==c.getColumn())
								                			    	   {
								                			    		   result.setText("   Game over bull catches full");
								                			    	JOptionPane.showMessageDialog(null,"Game over");
								                			    	
								                			    	   }
								                			    	   else
									                			       {
									                			    	 
														                    gp.getChildren().remove(l3);
														                	gp.add(l3, w.getRow(),w.getColumn());
									                			       }
								                			       }
								              
						                			    	   
						                					}
						                			       else 
						                			       {
						                			    	   if(row==c.getRow() & col==c.getColumn())
						                			    	   {
						                			    		   result.setText("   Game over bull catches full");
						                			    		JOptionPane.showMessageDialog(null,"Game over");
						                			    		
						                			    	   }
						                			    	   else
						                			    	   {
						                			    		  
												                    gp.getChildren().remove(l3);
												                	gp.add(l3, w.getRow(),w.getColumn());
						                			    		   
						                			    	   }
						                			       }
						                			  
						                			   
						                	}
						                	
						                	if(findvalue(c.getColumn()-1,c.getRow())=='W')
						                	{
						                		
						                	}
						                	else if(findvalue(c.getColumn(),c.getRow())=='E')
						                	{
						                		result.setText("  Congratulations You win the game !");
						                		JOptionPane.showMessageDialog(null, "Congratulations You win the game !");
						                		
						                	}
						                	else
						                	{
						                	c.setColumn(c.getColumn()-1);
						                    gp.getChildren().remove(f);
						                	gp.add(f, c.getRow(),c.getColumn());
						                	}
						                    
						                }
						                else if (ke.getCode()==KeyCode.RIGHT) 
						                {
						                	
						                	if(c.getColumn()>4 || c.getRow()>4 )
						                	{
						                		
						                		
						                				   row=row+1;
						                			       w.setRow(row);
						                			       
						                			       if(findvalue(w.getColumn(),w.getRow())=='W')
						                					{
						                			    	   row=row-1;
						                			    	   w.setRow(row);
						                			    	  
						                			    	  
						                			    	   col=col+1;
							                			       w.setColumn(col);;
								                			       if(findvalue(w.getColumn(),w.getRow())=='W')
								                					{
								                			    	   col=col-1;
								                			    	   w.setColumn(col);;
								                			    	   
								                					}
								                			       else  
								                			       {
								                			    	   if(row==c.getRow() & col==c.getColumn())
								                			    	   {
								                			    		   result.setText("   Game over bull catches full");
									                			    	   
								                			    		   JOptionPane.showMessageDialog(null,"Game over");
								                			    		  
								                			    		  
								                			    	   }
								                			    	   else
									                			       {
									                			    	 
														                    gp.getChildren().remove(l3);
														                	gp.add(l3, w.getRow(),w.getColumn());
									                			       }
								                			       }
								              
						                			    	   
						                					}
						                			       else 
						                			       {
						                			    	   if(row==c.getRow() & col==c.getColumn())
						                			    	   {
						                			    		   result.setText("   Game over bull catches full");
						                			    		   JOptionPane.showMessageDialog(null,"Game over");
						                			    		  
						                			    	   
						                			    	   }
						                			    	   else
						                			    	   {
												                    gp.getChildren().remove(l3);
												                	gp.add(l3, w.getRow(),w.getColumn());
						                			    		   
						                			    	   }
						                			       }
						                			  
						                			   
						                	}
						                	
						                	
						                	if(findvalue(c.getColumn(),c.getRow()+1)=='W')
						                	{
						                		
						                	}
						                	else if(findvalue(c.getColumn(),c.getRow())=='E')
						                	{
						                		result.setText("  Congratulations You win the game !");
						                		JOptionPane.showMessageDialog(null, "Congratulations You win the game !");
						                		
						                	}
						                	else
						                	{
						                	c.setRow(c.getRow()+1);
						                    gp.getChildren().remove(f);
						                	gp.add(f, c.getRow(),c.getColumn());
						                	}
						                    
						                }
						                else if (ke.getCode()==KeyCode.LEFT) 
						                {
						                	if(c.getColumn()>4 || c.getRow()>4 )
						                	{
						                		
						                		
						                				   row=row-1;
						                			       w.setRow(row);
						                			       
						                			       if(findvalue(w.getColumn(),w.getRow())=='W')
						                					{
						                			    	   row=row+1;
						                			    	   w.setRow(row);
						                			    	 
						                			    	  
						                			    	   col=col-1;
							                			       w.setColumn(col);;
								                			       if(findvalue(w.getColumn(),w.getRow())=='W')
								                					{
								                			    	   col=col+1;
								                			    	   w.setColumn(col);;
								                			    	  
								                					}
								                			       else  
								                			       {
								                			    	   if(row==c.getRow() & col==c.getColumn())
								                			    	   {
								                			    		   
								                			    		   result.setText("   Game over bull catches full");
								                			    		   JOptionPane.showMessageDialog(null,"Game over");
								                			    		  
								                			    	   }
								                			    	   else
									                			       {
									                			    	
														                    gp.getChildren().remove(l3);
														                	gp.add(l3, w.getRow(),w.getColumn());
									                			       }
								                			       }
								              
						                			    	   
						                					}
						                			       else 
						                			       {
						                			    	   if(row==c.getRow() & col==c.getColumn())
						                			    	   {
						                			    		  
						                			    		   result.setText("   Game over bull catches full");
						                			    		   JOptionPane.showMessageDialog(null,"Game over");
						                			    		  
						                			    	  
						                			    	   }
						                			    	   else
						                			    	   {
						                			    		  
												                    gp.getChildren().remove(l3);
												                	gp.add(l3, w.getRow(),w.getColumn());
						                			    		   
						                			    	   }
						                			       }
						                			  
						                			   
						                	}
						                	
						                	
						                	if(findvalue(c.getColumn(),c.getRow()-1)=='W')
						                	{
						                		
						                	}
						                	else if(findvalue(c.getColumn(),c.getRow())=='E')
						                	{
						                		result.setText("  Congratulations You win the game !");
						                		JOptionPane.showMessageDialog(null, "Congratulations You win the game !");
						                		
						                	}
						                	else
						                	{
						                	c.setRow(c.getRow()-1);
						                    gp.getChildren().remove(f);
						                	gp.add(f, c.getRow(),c.getColumn());
						                	}
						                    
						                }
						                
						            }
						        }
			    	  );
		
			    	
			      }
			
			    });	
		
	
		
		
		bp.setTop(tbox);
		bp.setCenter(gp);
		bp.setBottom(bbox);
		
		
		primarystage.setScene(sc);
		primarystage.show();
	}
	

	public char findvalue(int col, int row)
	{
		char v =' ';
		for(Coordinate c:co)
		{
			if(c.getRow()==row && c.getColumn()==col)
			{
				v=c.getValue();
			
			}
		}
		return v;
		
	}
	
	public void findindex(int col, int row)
	{
		int j =0;
		for(int i=0;i<co.size();i++)
		{
			if(co.get(i).getRow()==col && co.get(i).getColumn()==row)
			{
				if(co.get(i).getValue()=='B')
				{
					co.get(i).setValue('W');
					
				}
				else if(co.get(i).getValue()=='W')
				{
					co.get(i).setValue('B');
					
				}
			
			}
		}
	
		
	}


	private void While(boolean b) {
		// TODO Auto-generated method stub
		
	}

	
	
}


