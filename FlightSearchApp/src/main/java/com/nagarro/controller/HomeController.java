package com.nagarro.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.nagarro.constant.Constants;
import com.nagarro.dao.CSVDao;
import com.nagarro.entities.CSVData;
import com.nagarro.entities.UserRequest;


@Controller
public class HomeController {
	
	@Autowired
	ServletContext context ;
	
	@Autowired
	CSVDao csvDao;
	
	@RequestMapping("/home")
	public String home(Model model) {
		
		String srcDir=Constants.FILE_PATH;
		ArrayList<String> listOfFile = new ArrayList<String>();
		File folder=null;
		 try 
		 {
	            folder = new File(srcDir);
	            for (File file : folder.listFiles()) 
	            {
	                String name = file.getName();
	                if (!listOfFile.contains(name)) 
	                {
	                	listOfFile.add(name);
	                }
	            }
	     } 
		 catch (Exception e) 
		 {
	            e.printStackTrace();
	     }
		 
		 List<String> csvData=new ArrayList<String>();
		 ArrayList<String> arr;
		 
		 if (!listOfFile.isEmpty())
		 {
			//create BufferedReader to read csv file
				for (int i = 0; i < listOfFile.size(); i++ )
				{
					BufferedReader br=null;
					try {
						 br = new BufferedReader(new FileReader(srcDir+"/"+listOfFile.get(i)));
						String strLine ="";
						br.readLine();
						while((strLine = br.readLine()) != null) {
							if (!csvData.contains(strLine))
							{
								StringTokenizer token = new StringTokenizer(strLine, "|");
				                arr = new ArrayList(strLine.length());
				                while (token.hasMoreTokens()) {
				                    arr.add(token.nextToken());
				                }
				                Object[] objArr = arr.toArray();
				                
				                
				                // convert Object array to String array
				                String[] str = Arrays.copyOf(objArr, objArr.length,String[].class);
				                
				                CSVData row=new CSVData();
				                row.setFlightNo(str[0]);
				                row.setDeptLoc(str[1]);
				                row.setArrLoc(str[2]);
				                row.setValidTill(str[3]);
				                row.setFlightTime(Integer.parseInt(str[4]));
				                row.setFlightDur(Float.parseFloat(str[5]));
				                row.setFare(Integer.parseInt(str[6]));
				                row.setSeatAvailable(str[7]);
				                row.setClassAvailable(str[8]);
				                this.csvDao.save(row);
							}
						}
					}
					catch(Exception e)
					{
						
					}
					finally {
						try {
							br.close();
							
						} catch (IOException e) {
							
							e.printStackTrace();
						}
					}
				}
		 }
 
		
		String str="home";
				model.addAttribute("page", str);
		return "home";
	}
	
	@RequestMapping("/search")
	public String search(Model m, HttpServletRequest request, HttpServletResponse response) throws ParseException
	{
		UserRequest userRequest=new UserRequest();
		userRequest.setDepartureLoc(request.getParameter("deploc"));
		userRequest.setArrivalLoc(request.getParameter("arrloc"));
		userRequest.setFlightDate(request.getParameter("date"));
		userRequest.setFlightClass(request.getParameter("cls"));
		userRequest.setOutputPreference(request.getParameter("outpre"));
		List<CSVData> list=this.csvDao.getAll();
		RequestCheck rc= new RequestCheck();
		List<CSVData> res=rc.checkFlight(userRequest, list);
		m.addAttribute("rows", res);
		return "search";
	}
	
}
